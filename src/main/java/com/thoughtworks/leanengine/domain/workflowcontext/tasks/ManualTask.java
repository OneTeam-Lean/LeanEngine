package com.thoughtworks.leanengine.domain.workflowcontext.tasks;

import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Activity;
import java.time.LocalDateTime;
import java.util.Map;

public class ManualTask extends Activity {
  private String triggeredBy;
  private boolean isTriggered;

  public ManualTask() {
    super(ComponentType.MANUAL_TASK);
  }

  public ManualTask(String id, String name, LocalDateTime startTime, LocalDateTime endTime) {
    super(ComponentType.MANUAL_TASK, id, name, startTime, endTime);
  }

  @Override
  protected Map<String, Object> executeComponent(WorkflowInstanceContext workflowInstanceContext) {
    turnStatus(Status.BLOCKED);
    while (!isTriggered) {
      turnStatus(Status.SUCCESS);
    }
    return null;
  }
}
