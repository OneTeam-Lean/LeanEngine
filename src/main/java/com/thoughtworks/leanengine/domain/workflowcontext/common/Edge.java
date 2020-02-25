package com.thoughtworks.leanengine.domain.workflowcontext.common;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.DiagramType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Edge extends Diagram {
  private String flowId;
  private Position startPosition;
  private Position endPosition;

  public Edge() {
    super(DiagramType.EDGE);
  }

  private Edge(String flowId, Position startPosition, Position endPosition) {
    super(DiagramType.EDGE);
    this.flowId = flowId;
    this.startPosition = startPosition;
    this.endPosition = endPosition;
  }

  public static Edge of(String flowId, Position startPosition, Position endPosition) {
    return new Edge(flowId, startPosition, endPosition);
  }
}
