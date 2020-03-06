package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import java.util.List;
import java.util.Map;

public class ParallelGatewayTail extends Gateway {
  private List<String> fromComponentsIds;

  public ParallelGatewayTail() {
    super(ComponentType.PARALLEL_GATEWAY);
  }

  @Override
  public Map<String, Object> executeComponent(WorkflowInstanceContext workflowInstanceContext) {
    turnStatus(Status.SUCCESS);
    fromComponentsIds
        .stream()
        .forEach(
            componentId -> {
              if (workflowInstanceContext.getComponentDataById(componentId) == null) {
                turnStatus(Status.BLOCKED);
              }
            });
    return null;
  }
}
