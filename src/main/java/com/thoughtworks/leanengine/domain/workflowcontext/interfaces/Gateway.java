package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import lombok.Getter;

@Getter
public abstract class Gateway extends Component {
  private String firstFlowId; // Fixme:no user.
  private String secondFlowId;
  private Boolean isStartGateway;

  public Gateway(ComponentType componentType) {
    super(componentType);
  }
}
