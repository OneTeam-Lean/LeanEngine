package com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Activity extends Component {
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public Activity(ComponentType type) {
    super(type);
  }

  public Activity(
      ComponentType componentType,
      String id,
      String name,
      List<String> nextComponentIds,
      LocalDateTime startTime,
      LocalDateTime endTime) {
    super(id, componentType, nextComponentIds);
    this.startTime = startTime;
    this.endTime = endTime;
    setName(name);
  }
}
