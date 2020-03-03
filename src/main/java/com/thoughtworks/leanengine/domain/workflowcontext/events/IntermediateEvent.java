package com.thoughtworks.leanengine.domain.workflowcontext.events;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.EventType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Event;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Job;

public class IntermediateEvent extends Event implements Job {
  private EventType eventType;

  public IntermediateEvent() {
    super(ComponentType.INTERMEDIATE_EVENT);
  }

  @Override
  public void execute() {
    this.setStatus(Status.SUCCESS);
  }
}
