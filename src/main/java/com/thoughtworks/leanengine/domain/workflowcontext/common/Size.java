package com.thoughtworks.leanengine.domain.workflowcontext.common;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class Size implements Serializable {
  private int width;
  private int height;

  public Size() {}

  public Size(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public static Size of(int w, int h) {
    return new Size(w, h);
  }
}
