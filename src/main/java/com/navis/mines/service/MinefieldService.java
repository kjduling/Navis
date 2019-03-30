package com.navis.mines.service;

import com.navis.mines.model.Mine;
import com.navis.mines.persistence.MinefieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class MinefieldService
{
  @Autowired
  private MinefieldRepository minefieldRepository;

  public List<Mine> getMines()
  {
    return minefieldRepository.findAll();
  }

  public void clearField()
  {
    minefieldRepository.truncate();
  }

  public void store(List<Mine> mines)
  {
    minefieldRepository.saveAll(mines);
    minefieldRepository.flush();
  }

  public void loadMinefield(MultipartFile minefieldFile)
  throws IOException
  {
    List<Mine> mineList = readMines(minefieldFile.getInputStream());
    store(mineList);
  }

  public List<Mine> readMines(InputStream in)
  throws IOException
  {
    List<Mine> mineList = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    String line;
    while ((line = reader.readLine()) != null)
    {
      String[] values = line.split(" ");
      Mine.builder()
          .x(Float.parseFloat(values[0]))
          .y(Float.parseFloat(values[1]))
          .radius(Integer.parseInt(values[2]))
          .build();
    }
    return mineList;
  }
}
