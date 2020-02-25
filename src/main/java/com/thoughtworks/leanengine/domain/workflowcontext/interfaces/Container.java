package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Container extends Component {
  private String name;

  public Container(ComponentType componentType) {
    super(componentType);
  }

  public Container(ComponentType componentType, String name) {
    super(componentType);
    this.name = name;
  }
}
