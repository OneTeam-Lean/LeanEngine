package com.thoughtworks.leanengine.infra.common.exceptions;

public class WorkflowException extends RuntimeException {

  public WorkflowException(String msg) {
    super(msg);
  }

  public WorkflowException() {}
}
