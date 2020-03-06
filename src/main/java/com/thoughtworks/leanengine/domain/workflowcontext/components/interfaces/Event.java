package com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import lombok.Getter;

@Getter
public abstract class Event extends Component {
  private String name;

  public Event(ComponentType componentType) {
    super(componentType);
  }

  public Event(ComponentType componentType, String id, String name) {
    super(id, componentType);
    this.name = name;
  }
}
