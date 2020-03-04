package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;

public class ParallelGateway extends Gateway {
  public ParallelGateway() {
    super(ComponentType.PARALLEL_GATEWAY);
  }

  @Override
  public ComponentData execute(WorkflowInstanceContext workflowInstanceContext) {
    this.setStatus(Status.SUCCESS);
    return null;
  }

  @Override
  public Component nextComponent() {
    return null;
  }
}
