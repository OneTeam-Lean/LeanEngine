package com.thoughtworks.leanengine.domain.workflowcontext.workflow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowException;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Slf4j
@ToString
public class ComponentExecution {
  private String componentId;
  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;
  private Component component;
  private ComponentExecutionData componentExecutionData;

  public static ComponentExecution of(Component component) {
    ComponentExecution componentExecution = new ComponentExecution();
    componentExecution.component = component;
    componentExecution.componentId = component.getId();
    componentExecution.componentExecutionData = ComponentExecutionData.createBlockData();
    return componentExecution;
  }

  public Object getData(String key) {
    return componentExecutionData.getData().get(key);
  }

  public void updateData(
      LocalDateTime startDateTime, LocalDateTime endDateTime, ComponentExecutionData data) {
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.componentExecutionData = data;
  }

  @JsonIgnore
  public Status getStatus() {
    return componentExecutionData.getComponentExecutionStatus();
  }

  public void execute(WorkflowExecution workflowExecution) {
    if (isBlocked()) {
      return;
    }
    LocalDateTime startDateTime = LocalDateTime.now();
    ComponentExecutionData data = ComponentExecutionData.createRunningData();
    // TODO Achieve CANCEL
    try {
      log.info("now run Component id is:{} , name is :{}", componentId, component.getName());
      data = component.executeComponent(workflowExecution);
    } catch (WorkflowException we) {
      LocalDateTime endDateTime = LocalDateTime.now();
      updateExecuteFailed(startDateTime, endDateTime);
      log.warn(
          "Component execute failed, cause :{},\n and ExecutionData is: {}", we, this.toString());
    }
    LocalDateTime endDateTime = LocalDateTime.now();
    updateData(startDateTime, endDateTime, data);
  }

  private void updateExecuteFailed(LocalDateTime startDateTime, LocalDateTime endDateTime) {
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.componentExecutionData = ComponentExecutionData.createFailedData();
  }

  @JsonIgnore
  public boolean isBlocked() {
    return componentExecutionData != null
        && componentExecutionData.getComponentExecutionStatus() == Status.BLOCKED;
  }

  @JsonIgnore
  public boolean isCompleted() {
    return componentExecutionData != null && Status.isValidExecutedStatus(getStatus());
  }
}
