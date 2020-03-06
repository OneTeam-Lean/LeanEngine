package com.thoughtworks.leanengine.domain.workflowcontext.tasks;

import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.execution.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Activity;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

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
    Map<String, Object> data = newHashMap();
    while (!isTriggered) {}
    Random random = new Random();
    Integer randomNumber = random.nextInt(100);
    data.put("randomNumber", randomNumber);
    turnStatus(Status.SUCCESS);
    return data;
  }
}
