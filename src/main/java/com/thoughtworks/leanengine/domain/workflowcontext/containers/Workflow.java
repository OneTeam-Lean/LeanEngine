package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Workflow extends Container {
  private List<Lane> lanes;
  private List<Component> components;
  private List<Diagram> diagrams;

  public Workflow(
      String name,
      String workflowId,
      List<Lane> lanes,
      List<Component> components,
      List<Diagram> diagrams) {
    super(workflowId, name);
    this.lanes = lanes;
    this.components = components;
    this.diagrams = diagrams;
  }
}
