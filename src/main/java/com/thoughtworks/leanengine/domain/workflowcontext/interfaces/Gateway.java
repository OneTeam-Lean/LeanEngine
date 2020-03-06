package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import lombok.Getter;

@Getter
public abstract class Gateway extends Component {
  private String firstFlowId;
  private String secondFlowId;
  private String name;

  public Gateway(ComponentType componentType) {
    super(componentType);
  }

  public Gateway(
      ComponentType componentType, String firstFlowId, String secondFlowId, String name) {
    super(componentType);
    this.firstFlowId = firstFlowId;
    this.secondFlowId = secondFlowId;
    this.name = name;
  }
}
