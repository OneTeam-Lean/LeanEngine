package com.thoughtworks.leanengine.domain.workflowcontext.instance;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import lombok.Getter;

@Getter
public class WorkflowInstance {
  private Workflow workflow;
  private WorkflowInstanceContext workflowInstanceContext;
  private Component currentComponent;

  public static WorkflowInstance of(Workflow workflow) {
    WorkflowInstance workflowInstance = new WorkflowInstance();
    workflowInstance.workflow = workflow;
    workflowInstance.currentComponent = workflow.getComponents().get(0);
    workflowInstance.workflowInstanceContext = new WorkflowInstanceContext();
    return workflowInstance;
  }

  public void execute() {
    while (currentComponent != null) {
      workflowInstanceContext.addComponentData(currentComponent.execute(workflowInstanceContext));
      currentComponent = currentComponent.nextComponent();
    }
  }
}
