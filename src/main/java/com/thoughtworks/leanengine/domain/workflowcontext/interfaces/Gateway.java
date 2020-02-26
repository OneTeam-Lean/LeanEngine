package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;

public abstract class Gateway extends Component {

  public Gateway(ComponentType componentType) {
    super(componentType);
  }

  private String id;
}
