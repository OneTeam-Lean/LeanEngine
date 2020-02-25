package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import lombok.Getter;

@Getter
public class Lane extends Container {
  List<String> ComponentIds;

  public Lane(String name, List<String> componentIds) {
    super(name);
    ComponentIds = componentIds;
  }
}
