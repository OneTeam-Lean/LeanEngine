package com.thoughtworks.leanengine.domain.workflowcontext.tasks;

import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Activity;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import java.time.LocalDateTime;

public class AutoTask extends Activity {

  public AutoTask(
      String id, String name, Status status, LocalDateTime startTime, LocalDateTime endTime) {
    super(ComponentType.AUTO_TASK, id, name, status, startTime, endTime);
  }

  public AutoTask() {
    super(ComponentType.AUTO_TASK);
  }

  @Override
  public ComponentData execute(WorkflowInstanceContext workflowInstanceContext) {
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
