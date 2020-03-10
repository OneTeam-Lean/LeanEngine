package com.thoughtworks.leanengine.application;

import com.thoughtworks.leanengine.adapter.restapi.dto.WorkflowCreateRequestDTO;
import com.thoughtworks.leanengine.adapter.restapi.dto.WorkflowUpdateRequestDTO;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowExecution;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowService;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowExecuteFailedException;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowNameExistException;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowNotFoundException;
import java.util.List;
import java.util.Objects;
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

  public String saveWorkflow(WorkflowCreateRequestDTO workflowRequestDTO) {
    Workflow queryWorkflowByName =
        workflowService.queryWorkflowByName(workflowRequestDTO.getName());
    if (queryWorkflowByName != null) {
      throw new WorkflowNameExistException();
    }
    return workflowService.saveWorkflow(workflowRequestDTO.toDomain());
  }

  public void updateWorkflow(WorkflowUpdateRequestDTO requestDTO) {
    Workflow queryWorkflowById = workflowService.queryWorkflowById(requestDTO.getId());
    checkIdExistence(queryWorkflowById);
    Workflow queryWorkflowByName = workflowService.queryWorkflowByName(requestDTO.getName());
    if (queryWorkflowByName != null
        && !Objects.equals(requestDTO.getId(), queryWorkflowByName.getId())) {
      throw new WorkflowNameExistException();
    }
    workflowService.updateWorkflow(requestDTO.toDomain());
  }

  private void checkIdExistence(Workflow queryWorkflowById) {
    if (queryWorkflowById == null) {
      throw new WorkflowNotFoundException();
    }
  }

  public WorkflowExecution runWorkflow(String workflowId) {
    WorkflowExecution workflowExecution = workflowService.runWorkflow(workflowId);
    if (workflowExecution == null) {
      throw new WorkflowExecuteFailedException();
    }
    return workflowExecution;
  }

  public List<WorkflowExecution> getWorkflowExecutions(String workflowId) {
    Workflow workflow = workflowService.queryWorkflowById(workflowId);
    if (workflow == null) {
      throw new WorkflowNotFoundException();
    }
    return workflow.getWorkflowExecutions();
  }
}
