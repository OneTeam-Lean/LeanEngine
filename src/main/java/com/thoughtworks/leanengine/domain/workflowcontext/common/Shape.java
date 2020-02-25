package com.thoughtworks.leanengine.domain.workflowcontext.common;

import lombok.Data;

@Data(staticConstructor = "of")
public class Shape implements Diagram {
  private final String componentId;
  private final Size size;
  private final Position position;
}
