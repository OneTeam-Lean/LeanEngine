package com.thoughtworks.leanengine.domain.workflowcontext.tasks;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Activity;
import java.time.LocalDateTime;

public class ManualTask extends Activity {
  private String triggeredBy;

  public ManualTask() {
    super(ComponentType.MANUAL_TASK);
  }

  public ManualTask(
      String id, String name, Status status, LocalDateTime startTime, LocalDateTime endTime) {
    super(ComponentType.MANUAL_TASK, id, name, status, startTime, endTime);
  }
}
