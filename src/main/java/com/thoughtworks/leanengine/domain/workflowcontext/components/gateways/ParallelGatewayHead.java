package com.thoughtworks.leanengine.domain.workflowcontext.components.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Gateway;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import java.util.List;

public class ParallelGatewayHead extends Gateway {
  private List<String> nextComponentsId;

  public ParallelGatewayHead() {
    super(ComponentType.PARALLEL_GATEWAY);
  }
}
