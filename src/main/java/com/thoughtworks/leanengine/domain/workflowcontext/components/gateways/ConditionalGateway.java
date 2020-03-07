package com.thoughtworks.leanengine.domain.workflowcontext.components.gateways;

import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Gateway;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.ComponentExecutionData;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowExecution;
import java.util.Collections;
import java.util.Map;

public class ConditionalGateway extends Gateway {
  public ConditionalGateway() {
    super(ComponentType.CONDITIONAL_GATEWAY);
  }

  private String fromComponentId;

  public ConditionalGateway(String fromComponentId) {
    super(ComponentType.CONDITIONAL_GATEWAY);
    this.fromComponentId = fromComponentId;
  }

  @Override
  public ComponentExecutionData executeComponent(WorkflowExecution workflowExecution) {
    Map<String, Object> data = newHashMap();
    Integer result =
        (Integer) workflowExecution.getComponentExecutionData(fromComponentId, "randomNumber");
    if (result == null || result > 50) {
      data.put("nextFlowId", Collections.singletonList(this.getFirstFlowId()));

    } else {
      data.put("nextFlowId", Collections.singletonList(this.getSecondFlowId()));
    }
    return ComponentExecutionData.createSuccessData(data);
  }
}
