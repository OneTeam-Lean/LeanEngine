package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Job;

public class ParallelGateway extends Gateway implements Job {
  public ParallelGateway() {
    super(ComponentType.PARALLEL_GATEWAY);
  }

  @Override
  public void execute() {
    this.setStatus(Status.SUCCESS);
  }
}
