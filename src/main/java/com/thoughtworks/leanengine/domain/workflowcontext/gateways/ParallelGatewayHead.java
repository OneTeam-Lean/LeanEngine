package com.thoughtworks.leanengine.domain.workflowcontext.gateways;

import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Gateway;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ParallelGatewayHead extends Gateway {
  public ParallelGatewayHead() {
    super(ComponentType.PARALLEL_GATEWAY);
  }

  @Override
  public ComponentData execute(WorkflowInstanceContext workflowInstanceContext) {
    LocalDateTime localDateTime = LocalDateTime.now();
    Map<String, Object> data = newHashMap();
    data.put("isFromGateway", Boolean.TRUE);
    data.put("nextFlowId", Arrays.asList(this.getFirstFlowId(), this.getSecondFlowId()));
    this.setStatus(Status.SUCCESS);
    return new ComponentData(this.getId(), localDateTime, LocalDateTime.now(), true, data);
  }

  @Override
  public List<Component> nextComponent(Workflow workflow) {
    List<Component> nextComponents = new ArrayList<>();
    workflow
        .getComponents()
        .stream()
        .forEach(
            component -> {
              if (component.getId().equalsIgnoreCase(this.getFirstFlowId())
                  || component.getId().equalsIgnoreCase(this.getSecondFlowId())) {
                nextComponents.add(component);
              }
            });
    return nextComponents;
  }
}
