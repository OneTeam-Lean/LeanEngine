package com.thoughtworks.leanengine.domain.workflowcontext.enums;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

public enum Status {
  SUCCESS,
  FAILED,
  BLOCKED,
  PENDING,
  CANCELED,
  RUNNING;

  private static List<Status> completedStatus = newArrayList(SUCCESS, FAILED, CANCELED);
  private static List<Status> validExecutedStatus = newArrayList(SUCCESS, BLOCKED);

  public static boolean isCompletedStatus(Status status) {
    return completedStatus.stream().anyMatch(s -> s == status);
  }

  public static boolean isValidExecutedStatus(Status status) {
    return validExecutedStatus.stream().anyMatch(s -> s == status);
  }

  public static boolean isSuccess(Status status) {
    return SUCCESS == status;
  }
}
