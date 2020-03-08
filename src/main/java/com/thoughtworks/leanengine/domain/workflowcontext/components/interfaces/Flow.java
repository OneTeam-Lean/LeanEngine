package com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import java.util.List;
import lombok.Getter;

@Getter
public abstract class Flow extends Component {
  private String fromComponentId;

  public Flow(ComponentType componentType) {
    super(componentType);
  }

  public Flow(
      ComponentType componentType,
      String flowId,
      String fromComponentId,
      List<String> nextComponentIds) {
    super(flowId, componentType, nextComponentIds);
    this.fromComponentId = fromComponentId;
  }
}
