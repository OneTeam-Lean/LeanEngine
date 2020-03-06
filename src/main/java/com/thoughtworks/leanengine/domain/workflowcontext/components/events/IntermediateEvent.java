package com.thoughtworks.leanengine.domain.workflowcontext.components.events;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Event;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.EventType;

public class IntermediateEvent extends Event {
  private EventType eventType; // Fixme:no user

  public IntermediateEvent() {
    super(ComponentType.INTERMEDIATE_EVENT);
  }
}
