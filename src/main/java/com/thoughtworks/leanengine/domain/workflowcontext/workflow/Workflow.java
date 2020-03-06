package com.thoughtworks.leanengine.domain.workflowcontext.workflow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.execution.WorkflowExecution;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Workflow {
  private String id;
  private String name;
  private Status lastExecutionStatus;
  private List<Lane> lanes;
  private List<Component> components;
  private List<Diagram> diagrams;
  private List<WorkflowExecution> workflowExecutions;

  public Workflow(
      String name,
      String workflowId,
      List<Lane> lanes,
      List<Component> components,
      List<Diagram> diagrams,
      List<WorkflowExecution> executions) {
    this.id = workflowId;
    this.name = name;
    this.lanes = lanes;
    if (CollectionUtils.isEmpty(components)) {
      this.components = ImmutableList.of();
    } else {
      this.components = ImmutableList.copyOf(components);
    }
    this.diagrams = diagrams;
    this.workflowExecutions = executions;
    if (CollectionUtils.isEmpty(workflowExecutions)) {
      lastExecutionStatus = Status.PENDING;
    } else {
      lastExecutionStatus =
          workflowExecutions.get(workflowExecutions.size() - 1).getWorkflowExecutionStatus();
    }
  }

  public Workflow(
      String id,
      String name,
      List<Lane> lanes,
      List<Component> components,
      List<Diagram> diagrams) {
    this.id = id;
    this.name = name;
    this.lanes = lanes;
    this.components = components;
    this.diagrams = diagrams;
  }

  public Workflow(String name) {
    this.name = name;
  }

  @JsonIgnore
  public boolean isLastExecutionCompleted() {
    return Status.isCompletedStatus(lastExecutionStatus);
  }

  public Status execute() {
    WorkflowExecution workflowExecution = new WorkflowExecution(this);
    this.workflowExecutions.add(workflowExecution);
    this.lastExecutionStatus = workflowExecution.execute();
    return this.lastExecutionStatus;
  }
}
