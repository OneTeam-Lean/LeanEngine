package com.thoughtworks.leanengine.domain.workflowcontext.workflow;

import static com.google.common.collect.Lists.newArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

@Getter
@NoArgsConstructor
public class Workflow {
  private String id;
  private String name;
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
    Status status = getLastExecutedStatus();
    if (status == null) {
      return true;
    }
    return Status.isCompletedStatus(status);
  }

  @JsonIgnore
  public Status getLastExecutedStatus() {
    if (CollectionUtils.isEmpty(workflowExecutions)) {
      return null;
    }
    return workflowExecutions.get(workflowExecutions.size() - 1).getWorkflowExecutionStatus();
  }

  public Status execute() {
    WorkflowExecution workflowExecution = initWorkflowExecution();
    this.workflowExecutions.add(workflowExecution);
    return workflowExecution.execute();
  }

  public Status executeByStep() {
    if (!isLastExecutionCompleted()) {
      return workflowExecutions.get(workflowExecutions.size() - 1).executeByStep();
    }
    WorkflowExecution workflowExecution = initWorkflowExecution();
    this.workflowExecutions.add(workflowExecution);
    return workflowExecution.executeByStep();
  }

  private WorkflowExecution initWorkflowExecution() {
    WorkflowExecution workflowExecution = new WorkflowExecution(this);
    if (CollectionUtils.isEmpty(workflowExecutions)) {
      this.workflowExecutions = newArrayList();
    }
    return workflowExecution;
  }
}
