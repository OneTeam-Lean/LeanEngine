package com.thoughtworks.leanengine.domain.workflowcontext.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
  private int position_x;
  private int position_y;
}
