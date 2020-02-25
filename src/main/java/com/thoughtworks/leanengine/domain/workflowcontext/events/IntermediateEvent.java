package com.thoughtworks.leanengine.domain.workflowcontext.events;

import com.thoughtworks.leanengine.domain.workflowcontext.common.EventType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Event;

public class IntermediateEvent extends Event {
  private EventType eventType;

  public IntermediateEvent() {
    super(ComponentType.INTERMEDIATE_EVENT);
  }
}
