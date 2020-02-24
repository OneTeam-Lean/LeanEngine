package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.common.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import lombok.Getter;

@Getter
public class Lane extends Container {
  private List<Component> componentList;
  private List<Diagram> positions;

  public Lane(String name, List<Component> componentList, List<Diagram> positions) {
    super(name);
    this.componentList = componentList;
    this.positions = positions;
  }
}
