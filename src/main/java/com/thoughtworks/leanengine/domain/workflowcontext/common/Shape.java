package com.thoughtworks.leanengine.domain.workflowcontext.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Shape implements Diagram {
  private String componentId;
  private Size size;
  private Position position;
}
