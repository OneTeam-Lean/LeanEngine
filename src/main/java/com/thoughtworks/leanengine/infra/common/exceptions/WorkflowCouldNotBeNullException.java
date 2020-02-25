package com.thoughtworks.leanengine.infra.common.exceptions;

public class WorkflowCouldNotBeNullException extends RuntimeException {

  public WorkflowCouldNotBeNullException() {
    super("Workflow cloud not be null");
  }
}
