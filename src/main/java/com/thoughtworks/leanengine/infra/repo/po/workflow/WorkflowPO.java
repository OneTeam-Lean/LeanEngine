package com.thoughtworks.leanengine.infra.repo.po.workflow;

import com.thoughtworks.leanengine.domain.workflowcontext.common.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.infra.repo.po.PersistenceObject;
import com.thoughtworks.leanengine.infra.util.MapperUtil;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

@Document(collection = "workflow")
@Data
public class WorkflowPO implements PersistenceObject<Workflow> {
  @Id private String workflowId;
  private String name;
  private List<Lane> lanes;
  private List<Component> components;
  private List<Diagram> diagrams;

  @Override
  public Workflow toDomainModel() {
    return MapperUtil.map(this, Workflow.class);
  }

  public static WorkflowPO of(Workflow workflow) {
    WorkflowPO workflowPO = MapperUtil.map(workflow, WorkflowPO.class);
    if (StringUtils.isEmpty(workflowPO.getWorkflowId())) {
      workflowPO.setWorkflowId(UUID.randomUUID().toString());
    }
    return workflowPO;
  }
}
