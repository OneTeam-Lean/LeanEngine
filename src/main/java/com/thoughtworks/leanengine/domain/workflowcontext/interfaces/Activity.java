package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.common.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import java.time.LocalDateTime;

public abstract class Activity extends Component {
  private String id;
  private String name;
  private Status status;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public Activity(ComponentType type) {
    super(type);
  }

  public Activity(
      ComponentType componentType,
      String id,
      String name,
      Status status,
      LocalDateTime startTime,
      LocalDateTime endTime) {
    super(componentType);
    this.id = id;
    this.name = name;
    this.status = status;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public void before() {}

  public void done() {}
}
