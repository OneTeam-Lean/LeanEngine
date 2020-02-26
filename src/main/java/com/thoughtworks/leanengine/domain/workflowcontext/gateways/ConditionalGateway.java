package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import lombok.Data;

@Data
public class ConditionalGateway extends Gateway {
  public ConditionalGateway(ComponentType componentType) {
    super(componentType);
  }

  private String conditionDescription;
  private Boolean symbol;
}
