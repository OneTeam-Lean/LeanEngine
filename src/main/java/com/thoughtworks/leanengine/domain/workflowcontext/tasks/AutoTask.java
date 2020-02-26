package com.thoughtworks.leanengine.domain.workflowcontext.tasks;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Activity;
import java.time.LocalDateTime;

public class AutoTask extends Activity {

  public AutoTask(
      String id, String name, Status status, LocalDateTime startTime, LocalDateTime endTime) {
    super(ComponentType.AUTO_TASK, id, name, status, startTime, endTime);
  }

  public AutoTask() {
    super(ComponentType.AUTO_TASK);
  }
}
