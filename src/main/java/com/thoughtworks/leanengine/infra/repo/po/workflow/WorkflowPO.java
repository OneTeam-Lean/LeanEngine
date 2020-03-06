package com.thoughtworks.leanengine.infra.repo.po.workflow;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.execution.WorkflowExecution;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.Workflow;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowCouldNotBeNullException;
import com.thoughtworks.leanengine.infra.repo.po.PersistenceObject;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

@Document(collection = "workflow")
@Data
public class WorkflowPO implements PersistenceObject<Workflow> {
  @Id private String id;
  private String name;
  private Status lastExecuteStatus;
  private List<Lane> lanes;
  private List<Component> components;
  private List<Diagram> diagrams;
  private List<WorkflowExecution> workflowExecutions;

  @Override
  public Workflow toDomainModel() {
    return new Workflow(
        this.name, this.id, this.lanes, this.components, this.diagrams, this.workflowExecutions);
  }

  public static WorkflowPO of(Workflow workflow) {
    if (workflow == null) {
      throw new WorkflowCouldNotBeNullException();
    }
    WorkflowPO workflowPO = new WorkflowPO();
    workflowPO.setId(workflow.getId());
    workflowPO.setName(workflow.getName());
    workflowPO.setComponents(workflow.getComponents());
    workflowPO.setDiagrams(workflow.getDiagrams());
    workflowPO.setLanes(workflow.getLanes());
    workflowPO.setLastExecuteStatus(workflow.getLastExecutionStatus());
    workflowPO.setWorkflowExecutions(workflow.getWorkflowExecutions());
    if (StringUtils.isEmpty(workflowPO.getId())) {
      workflowPO.setId(UUID.randomUUID().toString());
    }
    return workflowPO;
  }
}
