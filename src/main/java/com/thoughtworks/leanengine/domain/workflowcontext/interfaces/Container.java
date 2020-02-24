package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import lombok.Data;

@Data
public abstract class Container implements Component {
  private String name;

  public Container(String name) {
    this.name = name;
  }
}
