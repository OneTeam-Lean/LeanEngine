package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.events.EndEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.events.IntermediateEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.events.StartEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.tasks.AutoTask;
import com.thoughtworks.leanengine.domain.workflowcontext.tasks.ManualTask;
import java.io.Serializable;
import lombok.Getter;

@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @Type(value = Workflow.class, name = "workflow"),
  @Type(value = Lane.class, name = "lane"),
  @Type(value = StartEvent.class, name = "startEvent"),
  @Type(value = EndEvent.class, name = "endEvent"),
  @Type(value = IntermediateEvent.class, name = "intermediateEvent"),
  @Type(value = AutoTask.class, name = "autoTask"),
  @Type(value = ManualTask.class, name = "manualTask")
})
public abstract class Component implements Serializable {
  private ComponentType componentType;

  public Component(ComponentType componentType) {
    this.componentType = componentType;
  }
}
