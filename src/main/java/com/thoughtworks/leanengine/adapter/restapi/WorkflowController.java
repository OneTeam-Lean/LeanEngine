package com.thoughtworks.leanengine.adapter.restapi;

import com.thoughtworks.leanengine.adapter.restapi.dto.WorkflowCreateRequestDTO;
import com.thoughtworks.leanengine.adapter.restapi.dto.WorkflowUpdateRequestDTO;
import com.thoughtworks.leanengine.application.WorkflowUserCase;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.Workflow;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflow")
@CrossOrigin
public class WorkflowController {
  private final WorkflowUserCase workflowUserCase;

  public WorkflowController(WorkflowUserCase workflowUserCase) {
    this.workflowUserCase = workflowUserCase;
  }

  @GetMapping("/{name}")
  public Workflow getWorkFlowByName(@PathVariable("name") String name) {
    return workflowUserCase.getWorkflowByName(name);
  }

  @PostMapping
  public String saveWorkflow(@RequestBody WorkflowCreateRequestDTO workflowRequestDTO) {
    return workflowUserCase.saveWorkflow(workflowRequestDTO);
  }

  @PutMapping
  public void updateWorkflow(@RequestBody WorkflowUpdateRequestDTO workflowUpdateRequestDTO) {
    workflowUserCase.updateWorkflow(workflowUpdateRequestDTO);
  }
}
