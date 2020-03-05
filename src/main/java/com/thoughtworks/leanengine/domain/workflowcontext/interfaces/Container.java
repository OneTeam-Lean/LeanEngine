package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Container {
  private String id;
  private String name;

  public Container(String name) {
    this.name = name;
  }
}
