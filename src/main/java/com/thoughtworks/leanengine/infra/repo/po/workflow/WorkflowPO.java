package com.thoughtworks.leanengine.infra.repo.po.workflow;

import static com.google.common.collect.Lists.newArrayList;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowExecution;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowCouldNotBeNullException;
import com.thoughtworks.leanengine.infra.repo.po.PersistenceObject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Document(collection = "workflow")
@Data
public class WorkflowPO implements PersistenceObject<Workflow> {
  @Id private String id;
  private String name;
  private List<Lane> lanes;
  private List<Component> components;
  private List<Diagram> diagrams;
  private List<WorkflowExecutionPO> workflowExecutionPOs;

  @Override
  public Workflow toDomainModel() {
    List<WorkflowExecution> workflowExecutions = newArrayList();
    if (!CollectionUtils.isEmpty(workflowExecutionPOs)) {
      workflowExecutions =
          this.workflowExecutionPOs
              .stream()
              .map(WorkflowExecutionPO::toDomainModel)
              .collect(Collectors.toList());
    }
    return new Workflow(
        this.name, this.id, this.lanes, this.components, this.diagrams, workflowExecutions);
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
    if (!CollectionUtils.isEmpty(workflow.getWorkflowExecutions())) {
      workflowPO.setWorkflowExecutionPOs(
          workflow
              .getWorkflowExecutions()
              .stream()
              .map(WorkflowExecutionPO::of)
              .collect(Collectors.toList()));
    }
    if (StringUtils.isEmpty(workflowPO.getId())) {
      workflowPO.setId(UUID.randomUUID().toString());
    }
    return workflowPO;
  }
}
