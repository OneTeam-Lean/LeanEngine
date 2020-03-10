package com.thoughtworks.leanengine.domain.workflowcontext.components.tasks;

import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.leanengine.domain.workflowcontext.components.interfaces.Activity;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.ComponentType;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.ComponentExecutionData;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowExecution;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AutoTask extends Activity {

  public AutoTask(
      String id,
      String name,
      LocalDateTime startTime,
      LocalDateTime endTime,
      List<String> nextComponentIds) {
    super(ComponentType.AUTO_TASK, id, name, nextComponentIds, startTime, endTime);
  }

  public AutoTask() {
    super(ComponentType.AUTO_TASK);
  }

  @Override
  public ComponentExecutionData executeComponent(WorkflowExecution workflowExecution) {
    Random random = new Random();
    Integer randomNumber = random.nextInt(100);
    Map<String, Object> data = newHashMap();
    try {
      Thread.sleep(randomNumber * 100L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    data.put("randomNumber", randomNumber);
    return ComponentExecutionData.createSuccessData(data);
  }
}
