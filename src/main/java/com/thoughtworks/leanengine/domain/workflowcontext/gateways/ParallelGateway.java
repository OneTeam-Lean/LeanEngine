package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import lombok.Data;

@Data
public class ParallelGateway extends Gateway {
  public ParallelGateway() {
    super(ComponentType.PARALLEL_GATEWAY);
  }

  @Override
  public void execute() {
    this.setStatus(Status.SUCCESS);
  }
}
