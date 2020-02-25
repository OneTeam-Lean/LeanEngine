package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;

public abstract class Flow extends Component {

  public Flow(ComponentType componentType) {
    super(componentType);
  }
}
