package com.navis.mines.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Solution
{
  @JsonProperty
  Mine mine;
  @JsonProperty
  int additionalMineCount;
  @JsonProperty
  List<Mine> additionalMineList;
}
