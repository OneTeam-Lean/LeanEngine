package com.thoughtworks.leanengine.domain.workflowcontext.components.gateways;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Gateway;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.ComponentExecutionData;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowExecution;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ConditionalGateway extends Gateway {

  public static final String GATEWAY_FLAG = "ConditionalGatewayFlag";

  public ConditionalGateway() {
    super(ComponentType.CONDITIONAL_GATEWAY);
  }

  private String fromComponentId;

  private int nextComponentIdx = 0;

  public ConditionalGateway(String fromComponentId) {
    super(ComponentType.CONDITIONAL_GATEWAY);
    this.fromComponentId = fromComponentId;
  }

  @Override
  public ComponentExecutionData executeComponent(WorkflowExecution workflowExecution) {
    Map<String, Object> data = newHashMap();
    int result = new Random().nextInt(100);
    if (result > 50) {
      data.put(GATEWAY_FLAG, false);
      this.nextComponentIdx = 0;
    } else {
      data.put(GATEWAY_FLAG, true);
      this.nextComponentIdx = 1;
    }
    try {
      Thread.sleep(5000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return ComponentExecutionData.createSuccessData(data);
  }

  @Override
  public List<String> getNextComponentIds() {
    return newArrayList(super.getNextComponentIds().get(nextComponentIdx));
  }
}
