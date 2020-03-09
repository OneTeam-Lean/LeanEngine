package com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.thoughtworks.leanengine.domain.workflowcontext.components.events.EndEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.components.events.IntermediateEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.components.events.StartEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.components.flows.SequenceFlow;
import com.thoughtworks.leanengine.domain.workflowcontext.components.gateways.ConditionalGateway;
import com.thoughtworks.leanengine.domain.workflowcontext.components.gateways.ParallelGatewayHead;
import com.thoughtworks.leanengine.domain.workflowcontext.components.tasks.AutoTask;
import com.thoughtworks.leanengine.domain.workflowcontext.components.tasks.ManualTask;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.ComponentExecutionData;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowExecution;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class Component implements Serializable {
  @JsonIgnore private ComponentType componentType;
  private String id;
  private List<String> nextComponentIds;
  private String name;

  public Component(ComponentType componentType) {
    this.componentType = componentType;
  }

  public Component(String id, ComponentType componentType, List<String> nextComponentIds) {
    this.id = id;
    this.componentType = componentType;
    this.nextComponentIds = nextComponentIds;
  }

  public ComponentExecutionData executeComponent(WorkflowExecution workflowExecution) {
    return ComponentExecutionData.createSuccessAndEmptyData();
  }

  public void setName(String name) {
    this.name = name;
  }
}
