package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowDataContext;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Workflow extends Container {
  private List<Lane> lanes;
  private List<Component> components;
  private List<Diagram> diagrams;
  private WorkflowDataContext workflowDataContext;

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
    this.id = workflowId;
    this.lanes = lanes;
    this.components = components;
    this.diagrams = diagrams;
    this.workflowDataContext = new WorkflowDataContext();
  }
}
