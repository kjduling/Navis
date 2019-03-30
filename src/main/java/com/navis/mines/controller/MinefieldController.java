package com.navis.mines.controller;


import com.navis.mines.model.Mine;
import com.navis.mines.service.MinefieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Minefield")
@RestController
@RequestMapping("/minefield")
@Slf4j
public class MinefieldController
{
  @Autowired
  private MinefieldService minefieldService;

  @ApiOperation(value = "List all mines",
                notes = "List all mines in the field",
                response = Mine.class,
                responseContainer = "List")
  @ApiResponses(value = {@ApiResponse(code = 200,
                                      message = "Success|OK"),
                         @ApiResponse(code = 500,
                                      message = "Internal Server Error")})
  @GetMapping(value = "/get",
              produces = APPLICATION_JSON_VALUE)
  public Collection<Mine> getMines()
  {
    log.debug("getMines");
    return minefieldService.getMines();
  }

  @PostMapping(value = "/upload",
               consumes = {"multipart/form-data"})
  @ApiResponses(value = {@ApiResponse(code = 200,
                                      message = "Success|OK"),
                         @ApiResponse(code = 400,
                                      message = "Bad Request"),
                         @ApiResponse(code = 500,
                                      message = "Internal Server Error")})
  public ResponseEntity<?> upload(@RequestParam MultipartFile file)
  {
    try
    {
      minefieldService.loadMinefield(file);
    }
    catch (NumberFormatException nfe)
    {
      return new ResponseEntity<>(BAD_REQUEST);
    }
    catch (IOException e)
    {
      return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(OK);
  }

  @DeleteMapping()
  @ApiResponses(value = {@ApiResponse(code = 200,
                                      message = "Success|OK"),
                         @ApiResponse(code = 500,
                                      message = "Internal Server Error")})
  public ResponseEntity<?> clearField()
  {
    log.debug("clearField");
    minefieldService.clearField();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
