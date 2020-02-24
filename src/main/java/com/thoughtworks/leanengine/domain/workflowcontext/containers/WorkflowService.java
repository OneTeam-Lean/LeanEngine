package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import org.springframework.stereotype.Service;

@Service
public class WorkflowService {

  public void saveWorkflow(Workflow workflow) {}

  public Workflow queryWorkflowByName(String name) {
    return new Workflow(name);
  }
}
