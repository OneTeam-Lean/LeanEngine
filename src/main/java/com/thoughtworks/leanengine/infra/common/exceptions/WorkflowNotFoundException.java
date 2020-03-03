package com.thoughtworks.leanengine.infra.common.exceptions;

public class WorkflowNotFoundException extends WorkflowException {
  public WorkflowNotFoundException() {
    super("Workflow cloud not be found");
  }
}
