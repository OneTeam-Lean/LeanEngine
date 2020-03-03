package com.thoughtworks.leanengine.infra.common.exceptions;

public class WorkflowCouldNotBeNullException extends WorkflowException {

  public WorkflowCouldNotBeNullException() {
    super("Workflow cloud not be null");
  }
}
