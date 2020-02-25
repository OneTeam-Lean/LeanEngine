package com.thoughtworks.leanengine.domain.workflowcontext.tasks;

import com.thoughtworks.leanengine.domain.workflowcontext.common.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Activity;
import java.time.LocalDateTime;

public class AutoTask extends Activity {

  public AutoTask(
      String id, String name, Status status, LocalDateTime startTime, LocalDateTime endTime) {
    super(id, name, status, startTime, endTime);
  }
}
