package com.thoughtworks.leanengine.domain.workflowcontext.workflow;

import static com.google.common.collect.Lists.newArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

@Getter
@NoArgsConstructor
public class WorkflowExecution {
  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;
  private Status workflowExecutionStatus;
  private List<ComponentExecution> componentExecutions;
  @JsonIgnore private Map<String, ComponentExecution> componentMap;
  private ThreadLocal<List<ComponentExecution>> executeByStepInfo;

  public WorkflowExecution(Workflow workflow) {
    this.workflowExecutionStatus = Status.RUNNING;
    this.componentExecutions =
        workflow.getComponents().stream().map(ComponentExecution::of).collect(Collectors.toList());
    if (!CollectionUtils.isEmpty(workflow.getComponents())) {
      this.componentMap =
          componentExecutions
              .stream()
              .collect(
                  Collectors.toMap(ComponentExecution::getComponentId, component -> component));
    }
    executeByStepInfo = new ThreadLocal<>();
    executeByStepInfo.set(newArrayList(componentExecutions.get(0)));
  }

  public WorkflowExecution(
      LocalDateTime startDateTime,
      LocalDateTime endDateTime,
      Status workflowExecutionStatus,
      List<ComponentExecution> componentExecutions) {
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.workflowExecutionStatus = workflowExecutionStatus;
    this.componentExecutions = componentExecutions;
    this.componentMap =
        componentExecutions
            .stream()
            .collect(Collectors.toMap(ComponentExecution::getComponentId, c -> c));
    // TODO revert execution status
  }

  @JsonIgnore
  public ComponentExecution getComponentExecutionById(String componentId) {
    return componentMap.get(componentId);
  }

  @JsonIgnore
  public Object getComponentExecutionData(String componentId, String key) {
    return componentMap.get(componentId).getData(key);
  }

  @JsonIgnore
  public Status getComponentExecutedStatus(String componentId) {
    return componentMap.get(componentId).getStatus();
  }

  public Status execute() {
    this.startDateTime = LocalDateTime.now();
    List<ComponentExecution> waitExecuteComponent = newArrayList(componentExecutions.get(0));
    while (!CollectionUtils.isEmpty(waitExecuteComponent)) {
      waitExecuteComponent.forEach(componentExecution -> componentExecution.execute(this));
      if (waitExecuteComponent
          .stream()
          .map(ComponentExecution::getStatus)
          .anyMatch(s -> s == Status.FAILED)) {
        turnExecutionToFailedStatus();
        return Status.FAILED;
      }
      waitExecuteComponent = getNextComponentExecutions(waitExecuteComponent);
    }
    turnExecutionToSuccessStatus();
    return Status.SUCCESS;
  }

  public Status executeByStep() {
    initExecutionData();
    List<ComponentExecution> waitExecuteComponent = executeByStepInfo.get();
    if (!CollectionUtils.isEmpty(waitExecuteComponent)) {
      waitExecuteComponent.forEach(componentExecution -> componentExecution.execute(this));
      if (waitExecuteComponent
          .stream()
          .map(ComponentExecution::getStatus)
          .anyMatch(s -> s == Status.FAILED)) {
        turnExecutionToFailedStatus();
        return Status.FAILED;
      }
      List<ComponentExecution> nextComponentExecutions =
          getNextComponentExecutions(waitExecuteComponent);
      if (CollectionUtils.isEmpty(nextComponentExecutions)) {
        turnExecutionToSuccessStatus();
        return Status.SUCCESS;
      }
      executeByStepInfo.set(nextComponentExecutions);
      return Status.RUNNING;
    }
    return Status.SUCCESS;
  }

  private List<ComponentExecution> getNextComponentExecutions(
      List<ComponentExecution> waitExecuteComponent) {
    return Stream.concat(
            waitExecuteComponent
                .stream()
                .filter(ComponentExecution::isCompleted)
                .flatMap(
                    componentExecution ->
                        componentExecution
                            .getComponent()
                            .getNextComponentIds()
                            .stream()
                            .map(this::getComponentExecutionById)),
            waitExecuteComponent.stream().filter(ComponentExecution::isBlocked))
        .collect(Collectors.toList());
  }

  private void initExecutionData() {
    if (this.startDateTime != null) {
      this.startDateTime = LocalDateTime.now();
    }
  }

  private void turnExecutionToSuccessStatus() {
    this.endDateTime = LocalDateTime.now();
    this.workflowExecutionStatus = Status.SUCCESS;
  }

  private void turnExecutionToFailedStatus() {
    this.endDateTime = LocalDateTime.now();
    this.workflowExecutionStatus = Status.FAILED;
  }
}
