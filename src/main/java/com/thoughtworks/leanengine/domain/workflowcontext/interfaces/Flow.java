package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import lombok.Getter;

@Getter
public abstract class Flow extends Component {
  private String flowId;
  private String fromComponentId;
  private String toComponentId;

  public Flow(ComponentType componentType) {
    super(componentType);
  }

  public Flow(
      ComponentType componentType, String flowId, String fromComponentId, String toComponentId) {
    super(componentType);
    this.flowId = flowId;
    this.fromComponentId = fromComponentId;
    this.toComponentId = toComponentId;
  }
}
