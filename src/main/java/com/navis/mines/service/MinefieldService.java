package com.navis.mines.service;

import com.navis.mines.model.Mine;
import com.navis.mines.model.Solution;
import com.navis.mines.persistence.MinefieldRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MinefieldService
{
  @Autowired
  private MinefieldRepository minefieldRepository;

  /**
   * Given a mine, determine if its blast radius will detonate another mine
   *
   * @param exploding    the mine that is exploding
   * @param potential    the mine that may explode given its proximity
   * @param affectedList a list of mines that have already been affected by the exploding mine, so they should be excluded
   * @return <EM>TRUE</EM> if the potential mine explodes, otherwise <EM>FALSE</EM>
   */
  static boolean detonate(Mine exploding, Mine potential, List<Mine> affectedList)
  {
    // Only work with mines that aren't exploded
    if (affectedList.contains(exploding))
      return false;

    // The potential mine must be untouched to additionalMineCount
    if (affectedList.contains(potential))
      return false;

    return detonate(exploding.getX(), exploding.getY(), exploding.getRadius(), potential.getX(), potential.getY());
  }

  /**
   * Given a mine, determine if its blast radius will detonate another mine
   *
   * @param x1 X position of mine 1
   * @param y1 Y position of mine 1
   * @param r  Blast radius of mine 1
   * @param x2 Y position of mine 2
   * @param y2 Y position of mine 2
   * @return <em>TRUE</em> if mine 2 will explode, <EM>FALSE</EM> otherwise
   */
  static boolean detonate(Float x1, Float y1, Float r, Float x2, Float y2)
  {
    double result = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    log.debug("Radius: {}, Distance: {}, result: {}", r, result, r <= result);
    return r <= result;
  }

  /**
   * Load all of the mines from the database
   *
   * @return a List of {@link Mine} objects
   */
  public List<Mine> getMines()
  {
    log.debug("loading mines from database");
    return minefieldRepository.findAll();
  }

  /**
   * Clear the mines from the database
   */
  public void clearField()
  {
    log.debug("clearing database");
    minefieldRepository.truncate();
  }

  /**
   * Store the list of mines to the database
   *
   * @param mines A list of {@link Mine} objects
   */
  public void store(List<Mine> mines)
  {
    log.debug("Storing {} mines to the database", mines.size());
    minefieldRepository.saveAll(mines);
    minefieldRepository.flush();
  }

  /**
   * Read the file input from the user and store the mines to the database
   *
   * @param minefieldFile a file containing all of the mines
   * @throws IOException thrown if there is a problem reading the file
   */
  public void loadMinefield(MultipartFile minefieldFile)
  throws IOException
  {
    log.debug("Reading in new file {}", minefieldFile.getName());
    List<Mine> mineList = readMines(minefieldFile.getInputStream());
    store(mineList);
  }

  /**
   * Read the input file from the user and build a minefield from it.
   *
   * @param in An input stream containing the data
   * @return A list of mines
   * @throws IOException thrown if there is a problem reading the input stream
   */
  public List<Mine> readMines(InputStream in)
  throws IOException
  {
    List<Mine> mineList = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    String line;
    while ((line = reader.readLine()) != null)
    {
      String[] values = line.split(" ");
      Mine mine = Mine.builder()
                      .x(Float.parseFloat(values[0]))
                      .y(Float.parseFloat(values[1]))
                      .radius(Float.parseFloat(values[2]))
                      .build();
      log.debug("Created mine {}", mine.toString());
      mineList.add(mine);
    }
    return mineList;
  }

  /**
   * Find all the solutions to the problem.
   *
   * @return A list of solutions
   */
  public List<Solution> solve()
  {
    List<Mine> minefield = minefieldRepository.findAll();
    List<Solution> solutionList = new ArrayList<>();

    for (Mine mine : minefield)
    {
      log.debug("Solving for {}", mine);
      solutionList.add(solveForMine(mine, minefield));
    }

    return solutionList;
  }

  /**
   * Given a mine, find out how many other mines will explode if it is detonated.  Recursively determine other mines affected in the chain reaction.
   *
   * @param mine      the mine to be exploded
   * @param minefield all of the mines in the field
   * @return a {@link Solution} to the problem
   */
  Solution solveForMine(Mine mine, List<Mine> minefield)
  {
    List<Mine> affectedMines = new ArrayList<>();

    for (Mine m : minefield)
      if (detonate(mine, m, affectedMines))
        affectedMines.add(m);

    Solution intermediate = null;
    for (Mine affected : affectedMines)
      intermediate = solveForMine(affected, minefield);

    if (intermediate != null)
      affectedMines.addAll(intermediate.getAdditionalMineList());

    Solution solution = new Solution();
    solution.setMine(mine);
    solution.setAdditionalMineCount(affectedMines.size());
    solution.setAdditionalMineList(affectedMines);
    return solution;
  }

}
