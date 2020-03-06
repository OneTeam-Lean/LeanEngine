package com.thoughtworks.leanengine.domain.workflowcontext.events;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.execution.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Event;
import java.util.List;
import java.util.Map;

public class EndEvent extends Event {

  public EndEvent() {
    super(ComponentType.END_EVENT);
  }

  public EndEvent(String id, String name) {
    super(ComponentType.END_EVENT, id, name);
  }

  @Override
  public Map<String, Object> executeComponent(WorkflowInstanceContext workflowInstanceContext) {
    return null;
  }

  @Override
  public List<String> getNextComponentIds() {
    return null;
  }
}
