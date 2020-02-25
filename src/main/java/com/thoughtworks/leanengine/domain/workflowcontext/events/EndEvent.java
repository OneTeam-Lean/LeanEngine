package com.thoughtworks.leanengine.domain.workflowcontext.events;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Event;

public class EndEvent extends Event {

  public EndEvent() {
    super(ComponentType.END_EVENT);
  }
}
