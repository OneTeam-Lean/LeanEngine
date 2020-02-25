package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Lane extends Container {
  private String laneId;
  private List<String> ComponentIds;

  public Lane() {
    super(ComponentType.LANE);
  }

  public Lane(String name, String laneId, List<String> componentIds) {
    super(ComponentType.LANE, name);
    this.laneId = laneId;
    ComponentIds = componentIds;
  }
}
