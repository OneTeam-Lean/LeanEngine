package com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import java.time.LocalDateTime;

public abstract class Activity extends Component {
  private String name; // Fixme:这些属性看起来是每个组件都需要的
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public Activity(ComponentType type) {
    super(type);
  }

  public Activity(
      ComponentType componentType,
      String id,
      String name,
      LocalDateTime startTime,
      LocalDateTime endTime) {
    super(id, componentType);
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
  }
}
