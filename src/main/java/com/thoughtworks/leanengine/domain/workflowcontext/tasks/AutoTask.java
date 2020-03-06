package com.thoughtworks.leanengine.domain.workflowcontext.tasks;

import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.execution.WorkflowInstanceContext;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Activity;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

public class AutoTask extends Activity {

  public AutoTask(String id, String name, LocalDateTime startTime, LocalDateTime endTime) {
    super(ComponentType.AUTO_TASK, id, name, startTime, endTime);
  }

  public AutoTask() {
    super(ComponentType.AUTO_TASK);
  }

  @Override
  protected Map<String, Object> executeComponent(WorkflowInstanceContext workflowInstanceContext) {
    Random random = new Random();
    Integer randomNumber = random.nextInt(100);
    Map<String, Object> data = newHashMap();
    data.put("randomNumber", randomNumber);
    turnStatus(Status.SUCCESS);
    return data;
  }
}
