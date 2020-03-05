package com.thoughtworks.leanengine.adapter.restapi;

import com.thoughtworks.leanengine.application.WorkflowInstanceUserCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instance")
public class WorkflowInstanceController {
  private final WorkflowInstanceUserCase workflowInstanceUserCase;

  public WorkflowInstanceController(WorkflowInstanceUserCase workflowInstanceUserCase) {
    this.workflowInstanceUserCase = workflowInstanceUserCase;
  }

  @PostMapping("/{workflowId}")
  public boolean WorkflowInstanceController(@PathVariable(value = "workflowId") String workflowId) {
    return workflowInstanceUserCase.runInstance(workflowId);
  }
}
