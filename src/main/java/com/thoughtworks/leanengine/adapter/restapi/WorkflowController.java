package com.thoughtworks.leanengine.adapter.restapi;

import com.thoughtworks.leanengine.application.WorkflowUserCase;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {
  private final WorkflowUserCase workflowUserCase;

  public WorkflowController(WorkflowUserCase workflowUserCase) {
    this.workflowUserCase = workflowUserCase;
  }

  @GetMapping("/{name}")
  public Workflow getWorkFlowByName(@PathVariable("name") String name) {
    return workflowUserCase.getWorkflowByName(name);
  }
}
