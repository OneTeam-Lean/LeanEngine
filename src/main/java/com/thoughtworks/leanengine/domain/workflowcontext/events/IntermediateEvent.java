package com.thoughtworks.leanengine.domain.workflowcontext.events;

import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.EventType;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Event;
import java.util.Map;

public class IntermediateEvent extends Event {
  private EventType eventType; // Fixme:no user

  public IntermediateEvent() {
    super(ComponentType.INTERMEDIATE_EVENT);
  }

  @Override
  public Map<String, Object> executeComponent(WorkflowInstanceContext workflowInstanceContext) {
    return null;
  }
}
