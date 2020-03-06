package com.thoughtworks.leanengine.domain.workflowcontext.workflow;

import static com.google.common.collect.Lists.newArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.execution.WorkflowExecution;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

@Getter
@NoArgsConstructor
public class Workflow {
  private String id;
  private String name;
  private Status lastExecutionStatus;
  private List<Lane> lanes;
  private List<Component> components;
  private List<Diagram> diagrams;
  @JsonIgnore private Map<String, Component> componentMap;
  private List<WorkflowExecution> workflowExecutions;

  private List<Status> completedStatus =
      newArrayList(Status.CANCELED, Status.FAILED, Status.SUCCESS);

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
    this.components = components;
    this.diagrams = diagrams;
    if (!CollectionUtils.isEmpty(components)) {
      this.componentMap =
          components.stream().collect(Collectors.toMap(Component::getId, component -> component));
    }
    this.workflowExecutions = executions;
    if (CollectionUtils.isEmpty(workflowExecutions)) {
      lastExecutionStatus = Status.PENDING;
    } else {
      lastExecutionStatus =
          workflowExecutions.get(workflowExecutions.size() - 1).getExecutionStatus();
    }
  }

  public boolean isLastExecutionCompleted() {
    return completedStatus.stream().anyMatch(status -> lastExecutionStatus.equals(status));
  }
}
