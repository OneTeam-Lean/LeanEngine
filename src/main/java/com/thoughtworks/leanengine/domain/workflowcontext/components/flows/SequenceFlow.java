package com.thoughtworks.leanengine.domain.workflowcontext.components.flows;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Flow;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import java.util.List;
import java.util.UUID;

public class SequenceFlow extends Flow {

  public SequenceFlow() {
    super(ComponentType.SEQUENCE_FLOW);
  }

  public SequenceFlow(
      String sequenceFlowId, String fromComponentId, List<String> nextComponentIds) {
    super(ComponentType.SEQUENCE_FLOW, sequenceFlowId, fromComponentId, nextComponentIds);
  }

  public SequenceFlow(String fromComponentId, List<String> nextComponentIds) {
    super(
        ComponentType.SEQUENCE_FLOW,
        UUID.randomUUID().toString(),
        fromComponentId,
        nextComponentIds);
  }
}
