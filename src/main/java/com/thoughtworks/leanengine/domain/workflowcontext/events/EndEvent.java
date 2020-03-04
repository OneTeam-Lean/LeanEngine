package com.thoughtworks.leanengine.domain.workflowcontext.events;

import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Event;

public class EndEvent extends Event {

  public EndEvent() {
    super(ComponentType.END_EVENT);
  }

  public EndEvent(String id, String name) {
    super(ComponentType.END_EVENT, id, name);
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
