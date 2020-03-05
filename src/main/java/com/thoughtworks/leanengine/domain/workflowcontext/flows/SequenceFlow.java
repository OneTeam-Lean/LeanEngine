package com.thoughtworks.leanengine.domain.workflowcontext.flows;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Flow;
import java.util.List;
import java.util.UUID;

public class SequenceFlow extends Flow {

  public SequenceFlow() {
    super(ComponentType.SEQUENCE_FLOW);
  }

  public SequenceFlow(String sequenceFlowId, String fromComponentId, String toComponentId) {
    super(ComponentType.SEQUENCE_FLOW, sequenceFlowId, fromComponentId, toComponentId);
  }

  public SequenceFlow(String fromComponentId, String toComponentId) {
    super(
        ComponentType.SEQUENCE_FLOW, UUID.randomUUID().toString(), fromComponentId, toComponentId);
  }

  @Override
  public ComponentData execute(WorkflowInstanceContext workflowInstanceContext) {
    return null;
  }

  @Override
  public List<Component> nextComponent(Workflow workflow) {
    return null;
  }
}
