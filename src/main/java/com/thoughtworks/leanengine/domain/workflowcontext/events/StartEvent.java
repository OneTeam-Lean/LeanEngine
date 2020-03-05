package com.thoughtworks.leanengine.domain.workflowcontext.events;

import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Event;
import java.util.Map;

public class StartEvent extends Event {

  public StartEvent() {
    super(ComponentType.START_EVENT);
  }

  public StartEvent(String id, String name) {
    super(ComponentType.START_EVENT, id, name);
  }

  @Override
  public Map<String, Object> executeComponent(WorkflowInstanceContext workflowInstanceContext) {
    return null;
  }
}
