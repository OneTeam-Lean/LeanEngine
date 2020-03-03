package com.thoughtworks.leanengine.domain.workflowcontext.events;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Event;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Job;

public class EndEvent extends Event implements Job {

  public EndEvent() {
    super(ComponentType.END_EVENT);
  }

  public EndEvent(String id, String name) {
    super(ComponentType.END_EVENT, id, name);
  }

  @Override
  public void execute() {
    this.setStatus(Status.SUCCESS);
  }
}
