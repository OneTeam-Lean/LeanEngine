package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Job;
import lombok.Data;

@Data
public class ParallelGateway extends Gateway implements Job {
  public ParallelGateway(ComponentType componentType) {
    super(componentType);
  }

  @Override
  public void execute() {
    this.setStatus(Status.SUCCESS);
  }
}
