package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import java.util.Arrays;
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
  protected Map<String, Object> executeComponent(WorkflowInstanceContext workflowInstanceContext) {
    Map<String, Object> data = newHashMap();
    Integer result =
        (Integer)
            workflowInstanceContext
                .getComponentDataById(fromComponentId)
                .getData()
                .get("randomNumber");
    if (result == null || result > 50) {
      data.put("nextFlowId", Arrays.asList(this.getFirstFlowId()));

    } else {
      data.put("nextFlowId", Arrays.asList(this.getSecondFlowId()));
    }
    turnStatus(Status.SUCCESS);
    return data;
  }
}
