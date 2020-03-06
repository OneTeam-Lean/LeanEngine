package com.thoughtworks.leanengine.adapter.restapi.dto;

import com.thoughtworks.leanengine.adapter.DataTransferObject;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.Workflow;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WorkflowCreateRequestDTO implements DataTransferObject {
  private String name;

  public Workflow toDomain() {
    return new Workflow(name);
  }
}
