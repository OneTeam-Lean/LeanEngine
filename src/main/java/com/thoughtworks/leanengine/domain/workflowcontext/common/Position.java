package com.thoughtworks.leanengine.domain.workflowcontext.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {
  private int position_x;
  private int position_y;

  public static Position of(int x, int y) {
    return new Position(x, y);
  }
}
