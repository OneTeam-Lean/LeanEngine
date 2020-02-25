package com.thoughtworks.leanengine.domain.workflowcontext.tasks;

import com.thoughtworks.leanengine.domain.workflowcontext.common.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Activity;
import java.time.LocalDateTime;

public class ManualTask extends Activity {
  private String triggeredBy;

  public ManualTask(
      String id, String name, Status status, LocalDateTime startTime, LocalDateTime endTime) {
    super(id, name, status, startTime, endTime);
  }
}
