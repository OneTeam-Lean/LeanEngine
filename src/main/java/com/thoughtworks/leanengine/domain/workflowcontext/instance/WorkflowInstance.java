package com.thoughtworks.leanengine.domain.workflowcontext.instance;

import static com.google.common.collect.Lists.newArrayList;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import java.util.List;
import lombok.Getter;

@Getter
public class WorkflowInstance {
  private Workflow workflow;
  private WorkflowInstanceContext workflowInstanceContext;

  public static WorkflowInstance of(Workflow workflow) {
    WorkflowInstance workflowInstance = new WorkflowInstance();
    workflowInstance.workflow = workflow;
    workflowInstance.workflowInstanceContext = new WorkflowInstanceContext();
    return workflowInstance;
  }

  public void execute() {
    List<Component> needExecuteComponent = newArrayList(workflow.getComponents().get(0));
    // TODO
  }
}
