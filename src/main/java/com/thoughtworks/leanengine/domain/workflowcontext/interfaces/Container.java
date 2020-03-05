package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Container {
  private String Id;
  private String name;

  public Container(String name) {
    this.name = name;
  }
}
