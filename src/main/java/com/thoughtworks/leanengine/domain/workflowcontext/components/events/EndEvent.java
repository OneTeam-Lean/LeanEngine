package com.thoughtworks.leanengine.domain.workflowcontext.components.events;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Event;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import java.util.List;

public class EndEvent extends Event {

  public EndEvent() {
    super(ComponentType.END_EVENT);
  }

  public EndEvent(String id, String name, List<String> nextComponentIds) {
    super(ComponentType.END_EVENT, id, name, nextComponentIds);
  }
}
