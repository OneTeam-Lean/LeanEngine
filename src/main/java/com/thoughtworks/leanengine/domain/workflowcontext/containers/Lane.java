package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import lombok.Getter;

@Getter
public class Lane extends Container {
  private String laneId;
  private List<String> ComponentIds;

  public Lane(String name, String laneId, List<String> componentIds) {
    super(name);
    this.laneId = laneId;
    ComponentIds = componentIds;
  }
}
