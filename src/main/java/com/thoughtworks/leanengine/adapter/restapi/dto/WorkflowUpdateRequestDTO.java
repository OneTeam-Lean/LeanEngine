package com.thoughtworks.leanengine.adapter.restapi.dto;

import com.thoughtworks.leanengine.adapter.DataTransferObject;
import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.Workflow;
import java.util.List;
import lombok.Getter;

@Getter
public class WorkflowUpdateRequestDTO implements DataTransferObject {
  private String id;
  private String name;
  private List<Lane> lanes;
  private List<Component> components;
  private List<Diagram> diagrams;

  public Workflow toDomain() {
    return new Workflow(id, name, lanes, components, diagrams);
  }
}
