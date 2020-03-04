package com.thoughtworks.leanengine.domain.workflowcontext.events;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Event;

public class StartEvent extends Event {

  public StartEvent() {
    super(ComponentType.START_EVENT);
  }

  public StartEvent(String id, String name) {
    super(ComponentType.START_EVENT, id, name);
  }

  @Override
  public void execute() {
    this.setStatus(Status.SUCCESS);
  }
}
