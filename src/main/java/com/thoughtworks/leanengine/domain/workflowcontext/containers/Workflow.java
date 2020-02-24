package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.common.Edge;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Flow;
import java.util.List;
import lombok.Getter;

@Getter
public class Workflow extends Container {
  private String workflowId;
  private List<Lane> lanes;
  private List<Flow> laneFlow;
  private List<Edge> laneFlowEdge;

  public Workflow(String name, List<Lane> lanes, List<Flow> laneFlow, List<Edge> laneFlowEdge) {
    super(name);
    this.lanes = lanes;
    this.laneFlow = laneFlow;
    this.laneFlowEdge = laneFlowEdge;
  }
}
