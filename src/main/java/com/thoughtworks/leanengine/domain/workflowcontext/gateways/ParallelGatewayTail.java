package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import java.util.Map;

public class ParallelGatewayTail extends Gateway {
  private int counter = 0;

  public ParallelGatewayTail() {
    super(ComponentType.PARALLEL_GATEWAY);
  }

  @Override
  public Map<String, Object> executeComponent(WorkflowInstanceContext workflowInstanceContext) {
    this.counter++;
    if (counter < 2) {
      turnStatus(Status.PENDING);
      return null;
    }
    turnStatus(Status.SUCCESS);
    return null;
  }
}
