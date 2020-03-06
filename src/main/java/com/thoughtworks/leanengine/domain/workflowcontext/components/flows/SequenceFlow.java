package com.thoughtworks.leanengine.domain.workflowcontext.components.flows;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Flow;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
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
}
