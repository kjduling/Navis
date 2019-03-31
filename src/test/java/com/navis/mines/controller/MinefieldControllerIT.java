package com.navis.mines.controller;

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

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
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

  @Autowired
  private TestRestTemplate template;

  @Before
  public void setUp()
  throws Exception
  {
    fileUploadEndpoint = new URL("http://localhost:" + port + contextPath + endpoint + "/upload");
  }

  @Test
  public void fileUpload()
  {
    String text = IOUtils.toString(getClass().getClassLoader()
                                             .getResourceAsStream("data/Field1.txt"), StandardCharsets.UTF_8);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(TEXT_PLAIN);
    HttpEntity<String> entity = new HttpEntity<>(text, headers);
    ResponseEntity<String> result = template.postForEntity(fileUploadEndpoint.toString(), entity, String.class);
    assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
  }
}
