package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Workflow extends Container {
  private List<Lane> lanes;
  private List<Component> components;
  private List<Diagram> diagrams;
  private WorkflowInstanceContext workflowInstanceContext;

  public Workflow(
      String name,
      String workflowId,
      List<Lane> lanes,
      List<Component> components,
      List<Diagram> diagrams) {
    super(name);
    this.setId(workflowId);
    this.lanes = lanes;
    this.components = components;
    this.diagrams = diagrams;
    this.workflowInstanceContext = new WorkflowInstanceContext();
  }

  public ComponentData execute(WorkflowInstanceContext workflowInstanceContext) {
    return null;
  }
}
