package com.navis.mines.controller;

import com.navis.mines.model.Mine;
import com.navis.mines.persistence.MinefieldRepository;
import com.navis.mines.response.SolutionResponse;
import com.navis.mines.service.MinefieldService;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.TEXT_PLAIN;

@RunWith(SpringRunner.class)
@ActiveProfiles({"local"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MinefieldControllerIT
{

  @LocalServerPort
  private int port;

  private final String endpoint = "/v1/minefield";
  @Value("${server.servlet.context-path}")
  String contextPath;
  private URL fileUploadEndpoint;
  private URL solveEndpoint;

  @Autowired
  private TestRestTemplate template;

  @Autowired
  private MinefieldRepository minefieldRepository;

  @Autowired
  private MinefieldService minefieldService;

  @Before
  public void setUp()
  throws Exception
  {
    fileUploadEndpoint = new URL("http://localhost:" + port + contextPath + endpoint + "/upload");
    solveEndpoint = new URL("http://localhost:" + port + contextPath + endpoint + "/solve");
  }

  @Test
  public void fileUpload()
  {
    String text = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("data/Field1.txt"),
                                   StandardCharsets.UTF_8);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(TEXT_PLAIN);
    HttpEntity<String> entity = new HttpEntity<>(text, headers);
    ResponseEntity<String> result = template.postForEntity(fileUploadEndpoint.toString(), entity, String.class);
    assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
  }

  @Test
  public void solve()
  throws IOException
  {
    minefieldService.clearField();
    List<Mine> list = minefieldService.readMines(getClass().getClassLoader().getResourceAsStream("data/Field1.txt"));
    minefieldService.store(list);
    ResponseEntity<SolutionResponse> response = template.getForEntity(solveEndpoint.toString(), SolutionResponse.class);
    assertTrue(response.getStatusCode().equals(HttpStatus.OK));
  }
}
