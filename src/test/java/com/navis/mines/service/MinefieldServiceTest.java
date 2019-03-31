package com.navis.mines.service;

import com.navis.mines.model.Mine;
import com.navis.mines.persistence.MinefieldRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MinefieldServiceTest
{

  @Autowired
  MinefieldService minefieldService;

  @MockBean
  MinefieldRepository minefieldRepository;

  @Test
  public void detonate()
  {
    assertTrue(MinefieldService.detonate(1f, 1f, 1f, 2f, 2f));
    assertFalse(MinefieldService.detonate(2f, 2f, 1f, 1f, 1f));
  }

  @Test
  public void detonate1()
  {
  }

  @Test
  public void getMines()
  {
    when(minefieldRepository.findAll()).thenReturn(buildMinefield());
    List<Mine> field = minefieldService.getMines();
    assertFalse(field.isEmpty());
    assertEquals(field.size(), 2);
  }

  private List<Mine> buildMinefield()
  {
    List<Mine> mineList = new ArrayList<>();
    mineList.add(Mine.builder()
                     .x(1f)
                     .y(2f)
                     .radius(3f)
                     .build());
    mineList.add(Mine.builder()
                     .x(2f)
                     .y(3f)
                     .radius(4f)
                     .build());
    return mineList;
  }

  @Test
  public void clearField()
  {
  }

  @Test
  public void store()
  {
  }

  @Test
  public void loadMinefield()
  throws Exception
  {
    MockMultipartFile file = new MockMultipartFile("file", "filename.txt", TEXT_PLAIN_VALUE,
                                                   "10 20 30\n1 2 3".getBytes(StandardCharsets.UTF_8));
    minefieldService.loadMinefield(file);
  }

  @Test
  public void readMines()
  {
  }

  @Test
  public void solve()
  {
  }

  @Test
  public void solveForMine()
  {
  }
}