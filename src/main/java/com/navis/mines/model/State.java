package com.navis.mines.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum State
{
  PRIMED("Primed"), EXPLODING("Exploding"), SPENT("Spent");
  private String state;
}
