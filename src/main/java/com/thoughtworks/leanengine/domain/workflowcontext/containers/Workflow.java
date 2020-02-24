package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import lombok.Data;

@Data
public class Workflow extends Container {

  public Workflow(String name) {
    super(name);
  }
}
