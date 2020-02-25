package com.thoughtworks.leanengine.domain.workflowcontext.common;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.DiagramType;
import lombok.Getter;

@Getter
public class Shape extends Diagram {
  private String componentId;
  private Size size;
  private Position position;

  public Shape() {
    super(DiagramType.SHAPE);
  }

  public Shape(String componentId, Size size, Position position) {
    super(DiagramType.SHAPE);
    this.componentId = componentId;
    this.size = size;
    this.position = position;
  }

  public static Shape of(String componentId, Size size, Position position) {
    return new Shape(componentId, size, position);
  }
}
