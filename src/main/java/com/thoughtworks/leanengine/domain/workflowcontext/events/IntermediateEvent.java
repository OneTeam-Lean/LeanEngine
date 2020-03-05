package com.thoughtworks.leanengine.domain.workflowcontext.events;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.EventType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Event;
import java.util.Arrays;
import java.util.List;

public class IntermediateEvent extends Event {
  private EventType eventType;

  public IntermediateEvent() {
    super(ComponentType.INTERMEDIATE_EVENT);
  }

  @Override
  public ComponentData execute(WorkflowInstanceContext workflowInstanceContext) {
    this.setStatus(Status.SUCCESS);
    return null;
  }

  @Override
  public List<Component> nextComponent(Workflow workflow) {
    List<Component> components = workflow.getComponents();
    for (int i = 0; i < components.size(); i++) {
      if (components.get(i).getId().equalsIgnoreCase(this.getId())) {
        return Arrays.asList(components.get(i + 1));
      }
    }
    return null;
  }
}
