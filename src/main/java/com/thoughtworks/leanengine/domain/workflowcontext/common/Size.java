package com.thoughtworks.leanengine.domain.workflowcontext.common;

import lombok.Data;

@Data(staticConstructor = "of")
public class Size {
  private final int width;
  private final int height;
}
