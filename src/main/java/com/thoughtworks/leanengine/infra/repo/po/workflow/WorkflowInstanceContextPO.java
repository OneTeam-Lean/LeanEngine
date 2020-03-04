package com.thoughtworks.leanengine.infra.repo.po.workflow;

import static com.google.common.collect.Lists.newArrayList;

import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.infra.repo.po.PersistenceObject;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.annotation.Id;

public class WorkflowInstanceContextPO implements PersistenceObject<WorkflowInstanceContext> {
  @Id private String workflowId;
  private List<ComponentData> dataList = newArrayList();

  public static WorkflowInstanceContextPO of(String workflowId, WorkflowInstanceContext context) {
    WorkflowInstanceContextPO po = new WorkflowInstanceContextPO();
    po.dataList = context.getDataList();
    po.workflowId = workflowId;
    return po;
  }

  @Override
  public WorkflowInstanceContext toDomainModel() {
    WorkflowInstanceContext workflowInstanceContext = new WorkflowInstanceContext();
    workflowInstanceContext.setDataList(dataList);
    workflowInstanceContext.setDataMap(
        dataList.stream().collect(Collectors.toMap(ComponentData::getComponentId, data -> data)));
    return workflowInstanceContext;
  }
}
