package com.navis.mines.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("Mine")
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "minefield",
       schema = "navis")
public class Mine
{
  @Id
  @ApiModelProperty(notes = "Database id",
                    name = "id",
                    required = true,
                    value = "0")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long id;

  @ApiModelProperty(name = "X",
                    value = "X Coordinate",
                    required = true)
  @Basic(optional = false)
  @JsonProperty
  private Float x;

  @ApiModelProperty(name = "Y",
                    value = "Y Coordinate",
                    required = true)
  @Basic(optional = false)
  @JsonProperty
  private Float y;

  @ApiModelProperty(name = "Radius",
                    value = "Blast Radius",
                    required = true)
  @Basic(optional = false)
  @JsonProperty
  private Float radius;
}
