package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;

public abstract class Gateway extends Component {
  private String firstFlowId;
  private String secondFlowId;

  public Gateway(ComponentType componentType) {
    super(componentType);
  }
}
