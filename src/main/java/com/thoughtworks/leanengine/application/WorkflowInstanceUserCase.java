package com.thoughtworks.leanengine.application;

import com.thoughtworks.leanengine.domain.workflowcontext.execution.WorkflowInstanceService;
import org.springframework.stereotype.Component;

@Component
public class WorkflowInstanceUserCase {
  private final WorkflowInstanceService workflowInstanceService;

  public WorkflowInstanceUserCase(WorkflowInstanceService workflowInstanceService) {
    this.workflowInstanceService = workflowInstanceService;
  }

  public boolean runInstance(String workflowId) {
    return workflowInstanceService.runInstance(workflowId);
  }
}
