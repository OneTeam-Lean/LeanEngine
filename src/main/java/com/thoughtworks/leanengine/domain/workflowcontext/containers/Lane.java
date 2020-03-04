package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

  @Override
  public ComponentData execute(WorkflowInstanceContext workflowInstanceContext) {
    return null;
  }

  @Override
  public Component nextComponent() {
    return null;
  }
}
