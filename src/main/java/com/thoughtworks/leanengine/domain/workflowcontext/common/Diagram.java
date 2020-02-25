package com.thoughtworks.leanengine.domain.workflowcontext.common;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.DiagramType;
import java.io.Serializable;
import lombok.Getter;

@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @Type(value = Shape.class, name = "shape"),
  @Type(value = Edge.class, name = "edge"),
})
public abstract class Diagram implements Serializable {
  private DiagramType diagramType;

  public Diagram(DiagramType diagramType) {
    this.diagramType = diagramType;
  }
}
