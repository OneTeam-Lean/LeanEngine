package com.thoughtworks.leanengine.domain.workflowcontext.enums;

public enum ComponentType {
  // flow
  SEQUENCE_FLOW,
  // event
  START_EVENT,
  END_EVENT,
  INTERMEDIATE_EVENT,
  // task
  AUTO_TASK,
  MANUAL_TASK,
  // Gateway
  CONDITIONAL_GATEWAY,
  PARALLEL_GATEWAY,
}
