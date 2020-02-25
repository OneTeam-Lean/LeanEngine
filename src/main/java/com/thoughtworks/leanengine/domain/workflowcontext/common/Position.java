package com.thoughtworks.leanengine.domain.workflowcontext.common;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class Position implements Serializable {
  private int position_x;
  private int position_y;

  public Position(int position_x, int position_y) {
    this.position_x = position_x;
    this.position_y = position_y;
  }

  public Position() {}

  public static Position of(int x, int y) {
    return new Position(x, y);
  }
}
