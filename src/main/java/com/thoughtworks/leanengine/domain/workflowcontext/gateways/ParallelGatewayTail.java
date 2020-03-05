package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import java.util.List;
import lombok.Data;

@Data
public class ParallelGatewayTail extends Gateway {
  private int counter = 0;

  public ParallelGatewayTail() {
    super(ComponentType.PARALLEL_GATEWAY);
  }

  @Override
  public ComponentData execute(WorkflowInstanceContext workflowInstanceContext) {
    this.counter++;
    if (counter < 2) {
      this.setStatus(Status.PENDING);
      return null;
    }
    this.setStatus(Status.SUCCESS);
    return null;
  }

  @Override
  public List<Component> nextComponent(Workflow workflow) {
    return null;
  }
}
