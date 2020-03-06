package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.events.EndEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.events.IntermediateEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.events.StartEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.execution.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.execution.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.flows.SequenceFlow;
import com.thoughtworks.leanengine.domain.workflowcontext.gateways.ConditionalGateway;
import com.thoughtworks.leanengine.domain.workflowcontext.gateways.ParallelGatewayHead;
import com.thoughtworks.leanengine.domain.workflowcontext.tasks.AutoTask;
import com.thoughtworks.leanengine.domain.workflowcontext.tasks.ManualTask;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "componentType")
@JsonSubTypes({
  @Type(value = StartEvent.class, name = "START_EVENT"),
  @Type(value = EndEvent.class, name = "END_EVENT"),
  @Type(value = IntermediateEvent.class, name = "INTERMEDIATE_EVENT"),
  @Type(value = AutoTask.class, name = "AUTO_TASK"),
  @Type(value = ManualTask.class, name = "MANUAL_TASK"),
  @Type(value = SequenceFlow.class, name = "SEQUENCE_FLOW"),
  @Type(value = ConditionalGateway.class, name = "CONDITIONAL_GATEWAY"),
  @Type(value = ParallelGatewayHead.class, name = "PARALLEL_GATEWAY")
})
@Getter
public abstract class Component implements Serializable {
  @JsonIgnore private ComponentType componentType;
  private Status status;
  private String id;
  private List<String> nextComponentIds = newArrayList();

  public Component(ComponentType componentType) {
    this.componentType = componentType;
  }

  public Component(String id, ComponentType componentType) {
    this.id = id;
    this.componentType = componentType;
    this.status = Status.PENDING;
  }

  public final ComponentData execute(WorkflowInstanceContext workflowInstanceContext) {
    if (isBlocked()) {
      return null;
    }
    LocalDateTime startDateTime = LocalDateTime.now();
    Map<String, Object> data = newHashMap();
    try {
      data = executeComponent(workflowInstanceContext);
    } catch (WorkflowException we) {
      LocalDateTime endDateTime = LocalDateTime.now();
      return createComponentData(this.id, startDateTime, endDateTime, false, data);
    }
    LocalDateTime endDateTime = LocalDateTime.now();
    return createComponentData(this.id, startDateTime, endDateTime, true, data);
  }

  protected abstract Map<String, Object> executeComponent(
      WorkflowInstanceContext workflowInstanceContext);

  public void addNextComponent(String componentIds) {
    this.nextComponentIds.add(componentIds);
  }

  public void turnStatus(Status status) {
    this.status = status;
  }

  public List<String> nextComponentId() {
    return nextComponentIds;
  }

  public ComponentData createComponentData(
      String componentId,
      LocalDateTime startDateTime,
      LocalDateTime endDateTime,
      Boolean runningResult,
      Map<String, Object> data) {
    return new ComponentData(componentId, startDateTime, endDateTime, runningResult, data);
  }

  public boolean isBlocked() {
    return Status.BLOCKED == this.status;
  }
}
