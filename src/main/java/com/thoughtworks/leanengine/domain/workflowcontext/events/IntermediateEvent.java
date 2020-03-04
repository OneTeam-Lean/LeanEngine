package com.thoughtworks.leanengine.domain.workflowcontext.events;

import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.EventType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Event;

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
  public Component nextComponent() {
    return null;
  }
}
