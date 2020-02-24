package com.thoughtworks.leanengine.domain.workflowcontext.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Edge implements Diagram {
  private String flowId;
  private Position startPosition;
  private Position endPosition;
}
