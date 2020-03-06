package com.thoughtworks.leanengine.domain.workflowcontext.flows;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.execution.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Flow;
import java.util.Map;
import java.util.UUID;

public class SequenceFlow extends Flow {

  public SequenceFlow() {
    super(ComponentType.SEQUENCE_FLOW);
  }

  public SequenceFlow(String sequenceFlowId, String fromComponentId, String toComponentId) {
    super(ComponentType.SEQUENCE_FLOW, sequenceFlowId, fromComponentId, toComponentId);
  }

  public SequenceFlow(String fromComponentId, String toComponentId) { // Fixme: no user.
    super(
        ComponentType.SEQUENCE_FLOW, UUID.randomUUID().toString(), fromComponentId, toComponentId);
  }

  @Override
  public Map<String, Object> executeComponent(WorkflowInstanceContext workflowInstanceContext) {
    return null;
  }
}
