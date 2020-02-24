package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.common.Status;
import java.time.LocalDateTime;

public abstract class Activity implements Component {
  private String id;
  private String name;
  private Status status;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
}
