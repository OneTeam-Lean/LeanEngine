package com.thoughtworks.leanengine.domain.workflowcontext.diagrams;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.DiagramType;
import java.io.Serializable;
import lombok.Getter;

@Getter
@JsonTypeInfo(use = Id.NAME, property = "diagramType")
@JsonSubTypes({
  @Type(value = Shape.class, name = "SHAPE"),
  @Type(value = Edge.class, name = "EDGE"),
})
public abstract class Diagram implements Serializable {
  @JsonIgnore private DiagramType diagramType;

  public Diagram(DiagramType diagramType) {
    this.diagramType = diagramType;
  }
}
