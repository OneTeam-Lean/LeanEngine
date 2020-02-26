package com.thoughtworks.leanengine.domain.workflowcontext.gateway;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import lombok.Data;

@Data
public class ParallelGateway extends Gateway {
  public ParallelGateway(ComponentType componentType) {
    super(componentType);
  }
}
