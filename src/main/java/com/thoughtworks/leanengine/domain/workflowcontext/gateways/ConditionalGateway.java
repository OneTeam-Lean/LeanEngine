package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Job;
import lombok.Data;

@Data
public class ConditionalGateway extends Gateway implements Job {
  public ConditionalGateway(ComponentType componentType) {
    super(componentType);
  }

  private String conditionDescription;
  private Boolean symbol;

  @Override
  public void execute() {
    this.setStatus(Status.SUCCESS);
  }
}
