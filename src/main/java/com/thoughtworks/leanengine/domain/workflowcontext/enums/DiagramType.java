package com.thoughtworks.leanengine.domain.workflowcontext.enums;

import lombok.Getter;

@Getter
public enum DiagramType {
  SHAPE("SHAPE"),
  EDGE("EDGE");
  private String name;

  DiagramType(String name) {
    this.name = name;
  }
}
