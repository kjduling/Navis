package com.navis.mines.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.navis.mines.model.Solution;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SolutionResponse
{
  @JsonProperty
  List<Solution> solutionList;
}
