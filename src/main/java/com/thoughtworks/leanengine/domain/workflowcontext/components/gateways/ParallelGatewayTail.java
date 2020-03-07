package com.thoughtworks.leanengine.domain.workflowcontext.components.gateways;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Gateway;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.ComponentExecutionData;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowExecution;
import java.util.List;

public class ParallelGatewayTail extends Gateway {
  private List<String> fromComponentsIds;

  public ParallelGatewayTail() {
    super(ComponentType.PARALLEL_GATEWAY);
  }

  @Override
  public ComponentExecutionData executeComponent(WorkflowExecution workflowExecution) {
    boolean allSuccess =
        fromComponentsIds
            .stream()
            .map(workflowExecution::getComponentExecutedStatus)
            .allMatch(Status::isCompletedStatus);
    if (allSuccess) {
      return ComponentExecutionData.createSuccessAndEmptyData();
    }
    return ComponentExecutionData.createRunningData();
  }
}
