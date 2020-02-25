package com.thoughtworks.leanengine.domain.workflowcontext.common;

import lombok.Data;

@Data(staticConstructor = "of")
public class Edge implements Diagram {
  private final String flowId;
  private final Position startPosition;
  private final Position endPosition;
}
