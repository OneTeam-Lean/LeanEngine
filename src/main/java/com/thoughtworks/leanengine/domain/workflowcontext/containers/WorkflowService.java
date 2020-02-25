package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {

  public void saveWorkflow(Workflow workflow) {}

  public Workflow queryWorkflowByName(String name) {
    List<Lane> lanes = newArrayList();
    lanes.add(new Lane("testLane", null, newArrayList()));
    return new Workflow(null, name, lanes, newArrayList(), newArrayList());
  }
}
