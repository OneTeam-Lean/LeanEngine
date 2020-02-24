package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.common.Position;

public interface Positional {
  Position[] positions = new Position[2];

  default void setPosition(Position[] position) {
    positions[0] = position[0];
    positions[1] = position[1];
  }

  default boolean isPositionExist() {
    return positions[0] == null || positions[1] == null;
  }
}
