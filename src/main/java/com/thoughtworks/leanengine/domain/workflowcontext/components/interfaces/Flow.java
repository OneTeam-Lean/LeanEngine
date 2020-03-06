package com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import lombok.Getter;

@Getter
public abstract class Flow extends Component {
  private String fromComponentId;
  private String toComponentId;

  public Flow(ComponentType componentType) {
    super(componentType);
  }

  public Flow(
      ComponentType componentType, String flowId, String fromComponentId, String toComponentId) {
    super(flowId, componentType);
    this.fromComponentId = fromComponentId;
    this.toComponentId = toComponentId;
  }
}
