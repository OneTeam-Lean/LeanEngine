package com.thoughtworks.leanengine.domain.workflowcontext.common;

import lombok.Data;

@Data(staticConstructor = "of")
public class Position {
  private final int position_x;
  private final int position_y;
}
