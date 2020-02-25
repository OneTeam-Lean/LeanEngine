package com.thoughtworks.leanengine.domain.workflowcontext.events;

import com.thoughtworks.leanengine.domain.workflowcontext.common.EventType;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Event;
import lombok.Data;

@Data
public class IntermediateEvent extends Event {
  private EventType eventType;
}
