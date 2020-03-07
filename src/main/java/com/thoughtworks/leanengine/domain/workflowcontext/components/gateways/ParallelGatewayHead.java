package com.thoughtworks.leanengine.domain.workflowcontext.components.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Gateway;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;

public class ParallelGatewayHead extends Gateway {

  public ParallelGatewayHead() {
    super(ComponentType.PARALLEL_GATEWAY);
  }
}
