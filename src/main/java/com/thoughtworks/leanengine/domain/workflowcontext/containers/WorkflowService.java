package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.stereotype.Service;

@Service
public class WorkflowService {

  public void saveWorkflow(Workflow workflow) {}

  public Workflow queryWorkflowByName(String name) {
    return new Workflow(name, newArrayList(), newArrayList(), newArrayList());
  }
}
