package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;

public abstract class Event extends Component {
  private String id;
  private String taskId;
  private String name;

  public Event(ComponentType componentType) {
    super(componentType);
  }
}
