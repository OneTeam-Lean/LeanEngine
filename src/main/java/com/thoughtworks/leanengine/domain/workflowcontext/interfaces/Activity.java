package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import java.time.LocalDateTime;
import lombok.Data;

@Data
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
