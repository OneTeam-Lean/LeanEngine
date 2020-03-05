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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ConditionalGateway extends Gateway {
  public ConditionalGateway() {
    super(ComponentType.CONDITIONAL_GATEWAY);
  }

  private String conditionDescription;
  private Boolean goFirstFlow;

  @Override
  public ComponentData execute(WorkflowInstanceContext workflowInstanceContext) {
    LocalDateTime localDateTime = LocalDateTime.now();
    Map<String, Object> data = newHashMap();
    data.put("isFromGateway", Boolean.TRUE);
    if (goFirstFlow) {
      data.put("nextFlowId", Arrays.asList(this.getFirstFlowId()));
    } else {
      data.put("nextFlowId", Arrays.asList(this.getSecondFlowId()));
    }
    this.setStatus(Status.SUCCESS);

    return new ComponentData(this.getId(), localDateTime, LocalDateTime.now(), true, data);
  }

  @Override
  public List<Component> nextComponent(Workflow workflow) {
    return null;
  }
}
