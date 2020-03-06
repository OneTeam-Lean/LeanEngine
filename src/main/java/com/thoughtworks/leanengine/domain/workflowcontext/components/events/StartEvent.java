package com.thoughtworks.leanengine.domain.workflowcontext.components.events;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Event;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;

public class StartEvent extends Event {

  public StartEvent() {
    super(ComponentType.START_EVENT);
  }

  public StartEvent(String id, String name) {
    super(ComponentType.START_EVENT, id, name);
  }
}
