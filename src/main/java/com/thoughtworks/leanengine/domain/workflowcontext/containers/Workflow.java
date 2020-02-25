package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.common.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import lombok.Getter;

@Getter
public class Workflow extends Container {
  private String workflowId;
  private List<Lane> lanes;
  private List<Component> laneFlow;
  private List<Diagram> laneFlowEdge;

  public Workflow(
      String name, List<Lane> lanes, List<Component> laneFlow, List<Diagram> laneFlowEdge) {
    super(name);
    this.lanes = lanes;
    this.laneFlow = laneFlow;
    this.laneFlowEdge = laneFlowEdge;
  }
}
