package com.thoughtworks.leanengine.infra.repo.po.workflow;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.ComponentExecution;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowExecution;
import com.thoughtworks.leanengine.infra.repo.po.PersistenceObject;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class WorkflowExecutionPO implements PersistenceObject<WorkflowExecution> {
  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;
  private Status workflowExecutionStatus;
  private List<ComponentExecution> componentExecutions;

  @Override
  public WorkflowExecution toDomainModel() {
    return new WorkflowExecution(
        startDateTime, endDateTime, workflowExecutionStatus, componentExecutions);
  }

  public static WorkflowExecutionPO of(WorkflowExecution workflowExecution) {
    WorkflowExecutionPO workflowExecutionPO = new WorkflowExecutionPO();
    workflowExecutionPO.startDateTime = workflowExecution.getStartDateTime();
    workflowExecutionPO.endDateTime = workflowExecution.getEndDateTime();
    workflowExecutionPO.workflowExecutionStatus = workflowExecution.getWorkflowExecutionStatus();
    workflowExecutionPO.componentExecutions = workflowExecution.getComponentExecutions();
    return workflowExecutionPO;
  }
}
