package com.thoughtworks.leanengine.infra.conf;

import com.thoughtworks.leanengine.infra.common.exceptions.WhenCreateWorkflowItIdNotShouldBeExist;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowCouldNotBeNullException;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowNameExistException;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(
      value = {
        WhenCreateWorkflowItIdNotShouldBeExist.class,
        WorkflowCouldNotBeNullException.class,
        WorkflowNotFoundException.class,
        WorkflowNameExistException.class
      })
  protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = ex.getClass().getName();
    return handleExceptionInternal(
        ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }
}
