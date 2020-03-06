package com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import java.time.LocalDateTime;

public abstract class Activity extends Component {
  private String name;
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
