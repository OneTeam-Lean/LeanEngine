package com.thoughtworks.leanengine.application;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.WorkflowService;
import org.springframework.stereotype.Component;

@Component
public class WorkflowUserCase {
  private final WorkflowService workflowService;

  public WorkflowUserCase(WorkflowService workflowService) {
    this.workflowService = workflowService;
  }

  public Workflow getWorkflowByName(String name) {
    return workflowService.queryWorkflowByName(name);
  }

  public void saveWorkflow(Workflow workflow) {
    workflowService.saveWorkflow(workflow);
  }
}
