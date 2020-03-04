package com.thoughtworks.leanengine.domain.workflowcontext.tasks;

import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Activity;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import java.time.LocalDateTime;

public class ManualTask extends Activity {
  private String triggeredBy;
  private boolean isTriggered;

  public ManualTask() {
    super(ComponentType.MANUAL_TASK);
  }

  public ManualTask(
      String id, String name, Status status, LocalDateTime startTime, LocalDateTime endTime) {
    super(ComponentType.MANUAL_TASK, id, name, status, startTime, endTime);
  }

  @Override
  public ComponentData execute(WorkflowInstanceContext workflowInstanceContext) {
    while (!isTriggered) {
      continue;
    }
    this.setStartTime(LocalDateTime.now());
    this.setStatus(Status.SUCCESS);
    this.setEndTime(LocalDateTime.now());
    return null;
  }

  @Override
  public Component nextComponent() {
    return null;
  }
}
