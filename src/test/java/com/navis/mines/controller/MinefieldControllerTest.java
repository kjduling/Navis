package com.navis.mines.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"local"})
@AutoConfigureMockMvc
public class MinefieldControllerTest
{
  @Autowired
  private MockMvc mvc;

  @Test
  public void testUpload()
  throws Exception
  {
    ResultMatcher ok = status().isOk();

    MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "Field1.txt",
                                                                "text/plain", getClass().getClassLoader()
                                                                                        .getResourceAsStream(
                                                                                            "data/Field1.txt"));

    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.multipart("/v1/minefield/upload")
                              .file(mockMultipartFile);
    this.mvc.perform(builder).andExpect(ok)
            .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
  }
}
