package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Job;

public class ConditionalGateway extends Gateway implements Job {
  public ConditionalGateway() {
    super(ComponentType.CONDITIONAL_GATEWAY);
  }

  private String conditionDescription;
  private Boolean symbol;

  @Override
  public void execute() {
    this.setStatus(Status.SUCCESS);
  }
}
