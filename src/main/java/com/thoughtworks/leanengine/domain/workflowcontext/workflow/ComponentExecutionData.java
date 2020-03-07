package com.thoughtworks.leanengine.domain.workflowcontext.workflow;

import static com.thoughtworks.leanengine.domain.workflowcontext.enums.Status.BLOCKED;
import static com.thoughtworks.leanengine.domain.workflowcontext.enums.Status.FAILED;
import static com.thoughtworks.leanengine.domain.workflowcontext.enums.Status.RUNNING;
import static com.thoughtworks.leanengine.domain.workflowcontext.enums.Status.SUCCESS;

import com.google.common.collect.ImmutableMap;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor
public class ComponentExecutionData {
  private Status componentExecutionStatus;
  private ImmutableMap<String, Object> data;

  public static ComponentExecutionData createFailedData() {
    return new ComponentExecutionData(FAILED, ImmutableMap.of());
  }

  public static ComponentExecutionData createRunningData() {
    return new ComponentExecutionData(RUNNING, ImmutableMap.of());
  }

  public static ComponentExecutionData createSuccessAndEmptyData() {
    return new ComponentExecutionData(SUCCESS, ImmutableMap.of());
  }

  public static ComponentExecutionData createSuccessData(Map<String, Object> data) {
    return new ComponentExecutionData(SUCCESS, ImmutableMap.copyOf(data));
  }

  public static ComponentExecutionData createBlockData() {
    return new ComponentExecutionData(BLOCKED, ImmutableMap.of());
  }
}
