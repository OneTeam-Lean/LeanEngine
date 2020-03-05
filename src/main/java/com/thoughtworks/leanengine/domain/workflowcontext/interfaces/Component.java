package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.events.EndEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.events.IntermediateEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.events.StartEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.flows.SequenceFlow;
import com.thoughtworks.leanengine.domain.workflowcontext.gateways.ConditionalGateway;
import com.thoughtworks.leanengine.domain.workflowcontext.gateways.ParallelGatewayHead;
import com.thoughtworks.leanengine.domain.workflowcontext.tasks.AutoTask;
import com.thoughtworks.leanengine.domain.workflowcontext.tasks.ManualTask;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "componentType")
@JsonSubTypes({
  @Type(value = Workflow.class, name = "WORKFLOW"),
  @Type(value = Lane.class, name = "LANE"),
  @Type(value = StartEvent.class, name = "START_EVENT"),
  @Type(value = EndEvent.class, name = "END_EVENT"),
  @Type(value = IntermediateEvent.class, name = "INTERMEDIATE_EVENT"),
  @Type(value = AutoTask.class, name = "AUTO_TASK"),
  @Type(value = ManualTask.class, name = "MANUAL_TASK"),
  @Type(value = SequenceFlow.class, name = "SEQUENCE_FLOW"),
  @Type(value = ConditionalGateway.class, name = "CONDITIONAL_GATEWAY"),
  @Type(value = ParallelGatewayHead.class, name = "PARALLEL_GATEWAY")
})
public abstract class Component implements Serializable {
  @JsonIgnore private ComponentType componentType;
  protected Status status;
  protected String id;

  public Component(ComponentType componentType) {
    this.componentType = componentType;
  }

  public abstract ComponentData execute(WorkflowInstanceContext workflowInstanceContext);

  public abstract Component nextComponent();
}
