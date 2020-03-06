package com.thoughtworks.leanengine.domain.workflowcontext.execution;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import lombok.Getter;

@Getter
public class WorkflowExecution {
  private Status executionStatus;
}
