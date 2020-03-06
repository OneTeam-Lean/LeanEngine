package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import java.util.List;
import java.util.Map;

public class ParallelGatewayHead extends Gateway {
  private List<String> nextComponentsId;

  public ParallelGatewayHead() {
    super(ComponentType.PARALLEL_GATEWAY);
  }

  @Override
  public Map<String, Object> executeComponent(WorkflowInstanceContext workflowInstanceContext) {
    Map<String, Object> data = newHashMap();
    turnStatus(Status.SUCCESS);
    return data;
  }
}
