package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.common.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Workflow extends Container {
  private String workflowId;
  private List<Lane> lanes;
  private List<Component> components;
  private List<Diagram> diagrams;

  public Workflow() {
    super(ComponentType.WORKFLOW);
  }

  public Workflow(
      String name,
      String workflowId,
      List<Lane> lanes,
      List<Component> components,
      List<Diagram> diagrams) {
    super(ComponentType.WORKFLOW, name);
    this.workflowId = workflowId;
    this.lanes = lanes;
    this.components = components;
    this.diagrams = diagrams;
  }
}
