package com.thoughtworks.leanengine.domain.workflowcontext.components.tasks;

import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Activity;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.ComponentExecutionData;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowExecution;
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
  public ComponentExecutionData executeComponent(WorkflowExecution workflowExecution) {
    Map<String, Object> data = newHashMap();
    if (!isTriggered) {
      try {
        Thread.sleep(30000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return ComponentExecutionData.createBlockData();
    }
    Random random = new Random();
    Integer randomNumber = random.nextInt(100);
    data.put("randomNumber", randomNumber);
    return ComponentExecutionData.createSuccessData(data);
  }
}
