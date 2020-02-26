package com.thoughtworks.leanengine.infra.common.exceptions;

public class WorkflowNotFoundException extends RuntimeException {
  public WorkflowNotFoundException() {
    super("Workflow cloud not be found");
  }
}
