package com.thoughtworks.leanengine.domain.workflowcontext.instance;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public class WorkflowInstance {
  private Workflow workflow;
  private WorkflowInstanceContext workflowInstanceContext;
  private List<Component> currentComponent;

  public static WorkflowInstance of(Workflow workflow) {
    WorkflowInstance workflowInstance = new WorkflowInstance();
    workflowInstance.workflow = workflow;
    workflowInstance.currentComponent = Arrays.asList(workflow.getComponents().get(0));
    workflowInstance.workflowInstanceContext = new WorkflowInstanceContext();
    return workflowInstance;
  }

  public void execute() {
    currentComponent
        .stream()
        .forEach(
            component -> {
              while (currentComponent != null) {
                workflowInstanceContext.addComponentData(
                    component.execute(workflowInstanceContext));
                currentComponent = component.nextComponent(workflow);
              }
            });
  }
}
