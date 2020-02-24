package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import lombok.Data;

@Data
public abstract class Container implements Component {
  private String name;
  private List<Component> componentList = newArrayList();

  public Container(String name) {
    this.name = name;
  }

  public void addComponent(Component component) {
    this.componentList.add(component);
  }
}
