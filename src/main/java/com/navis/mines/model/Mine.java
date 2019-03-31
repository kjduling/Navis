package com.navis.mines.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("Mine")
@Getter
@Setter
@Entity
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

  @ApiModelProperty(name = "State",
                    notes = "State of the Mine",
                    required = true,
                    value = "0")
  @Basic(optional = false)
  @Enumerated
  @JsonProperty
  private State state;

  public static MineBuilder builder() { return new MineBuilder(); }

  public static final class MineBuilder
  {
    private Long id;
    private Float x;
    private Float y;
    private Float radius;
    private State state;

    private MineBuilder() {}

    public MineBuilder id(Long id)
    {
      this.id = id;
      return this;
    }

    public MineBuilder x(Float x)
    {
      this.x = x;
      return this;
    }

    public MineBuilder y(Float y)
    {
      this.y = y;
      return this;
    }

    public MineBuilder radius(Float radius)
    {
      this.radius = radius;
      return this;
    }

    public MineBuilder state(State state)
    {
      this.state = state;
      return this;
    }

    public Mine build()
    {
      Mine mine = new Mine();
      mine.setId(id);
      mine.setX(x);
      mine.setY(y);
      mine.setRadius(radius);
      mine.setState(state);
      return mine;
    }
  }
}
