package com.thoughtworks.leanengine.domain.workflowcontext.diagrams;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.DiagramType;
import lombok.Getter;

@Getter
public class Edge extends Diagram {
  private String flowId;
  private int sourceAnchor;
  private int targetAnchor;

  public Edge() {
    super(DiagramType.EDGE);
  }

  public Edge(String flowId, int sourceAnchor, int targetAnchor) {
    super(DiagramType.EDGE);
    this.flowId = flowId;
    this.sourceAnchor = sourceAnchor;
    this.targetAnchor = targetAnchor;
  }

  public static Edge of(String flowId, int sourceAnchor, int targetAnchor) {
    return new Edge(flowId, sourceAnchor, targetAnchor);
  }
}
