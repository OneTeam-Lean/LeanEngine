package com.thoughtworks.leanengine.domain.workflowcontext.instance;

import static com.google.common.collect.Lists.newArrayList;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

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
    List<Component> waitExecuteComponent = newArrayList(workflow.getComponents().get(0));
    while (!CollectionUtils.isEmpty(waitExecuteComponent)) {
      waitExecuteComponent
          .stream()
          .map(component -> component.execute(workflowInstanceContext))
          .filter(Objects::nonNull)
          .forEach(workflowInstanceContext::addComponentData);
      waitExecuteComponent = getNextExecuteComponents(waitExecuteComponent);
    }
  }

  private List<Component> getNextExecuteComponents(List<Component> waitExecuteComponent) {
    return waitExecuteComponent
        .stream()
        .flatMap(
            component -> {
              if (component.isBlocked()) {
                return newArrayList(component).stream();
              }
              return component
                  .getNextComponentIds()
                  .stream()
                  .map(id -> workflow.getComponentMap().get(id))
                  .collect(Collectors.toList())
                  .stream();
            })
        .collect(Collectors.toList());
  }
}
