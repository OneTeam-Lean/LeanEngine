package com.thoughtworks.leanengine.application;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.WorkflowService;
import com.thoughtworks.leanengine.infra.common.exceptions.WhenCreateWorkflowItIdNotShouldBeExist;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowNameExistException;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowNotFoundException;
import org.apache.commons.lang3.StringUtils;
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
    if (StringUtils.isNotEmpty(workflow.getId())) {
      throw new WhenCreateWorkflowItIdNotShouldBeExist();
    }
    Workflow queryWorkflowByName = workflowService.queryWorkflowByName(workflow.getName());
    checkNameDuplication(workflow, queryWorkflowByName);
    workflowService.saveWorkflow(workflow);
  }

  public void updateWorkflow(Workflow workflow) {
    Workflow queryWorkflowById = workflowService.queryWorkflowById(workflow.getId());
    checkIdExistence(queryWorkflowById);
    Workflow queryWorkflowByName = workflowService.queryWorkflowByName(workflow.getName());
    checkNameDuplication(workflow, queryWorkflowByName);
    workflowService.saveWorkflow(workflow);
  }

  private void checkIdExistence(Workflow queryWorkflowById) {
    if (queryWorkflowById == null) {
      throw new WorkflowNotFoundException();
    }
  }

  private void checkNameDuplication(Workflow updateWorkflow, Workflow queryWorkflowByName) {
    if (queryWorkflowByName != null
        && !(updateWorkflow.getId().equals(queryWorkflowByName.getId()))) {
      throw new WorkflowNameExistException();
    }
  }
}
