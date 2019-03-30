package com.navis.mines.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@ApiModel("Mine")
@Getter
@Setter
@Builder
@Entity
@Table(name = "minefield",
       schema = "navis")
public class Mine
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NotNull
  private Long id;

  @ApiModelProperty(name = "X",
                    value = "X Coordinate",
                    required = true)
  @Basic(optional = false)
  private Float x;

  @ApiModelProperty(name = "Y",
                    value = "Y Coordinate",
                    required = true)
  @Basic(optional = false)
  private Float y;

  @ApiModelProperty(name = "Radius",
                    value = "Blast Radius",
                    required = true)
  @Basic(optional = false)
  private Integer radius;

  @ApiModelProperty(name = "State",
                    value = "State of the Mine",
                    required = true)
  @Basic(optional = false)
  @Enumerated
  private State state;
}
