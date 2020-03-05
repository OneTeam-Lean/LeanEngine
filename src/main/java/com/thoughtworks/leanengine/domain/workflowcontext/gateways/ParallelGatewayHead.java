package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import java.util.Arrays;
import java.util.Map;

public class ParallelGatewayHead extends Gateway {
  public ParallelGatewayHead() {
    super(ComponentType.PARALLEL_GATEWAY);
  }

  @Override
  public Map<String, Object> executeComponent(WorkflowInstanceContext workflowInstanceContext) {
    Map<String, Object> data = newHashMap();
    data.put("isFromGateway", Boolean.TRUE);
    data.put("nextFlowId", Arrays.asList(this.getFirstFlowId(), this.getSecondFlowId()));
    turnStatus(Status.SUCCESS);
    return data;
  }
}
