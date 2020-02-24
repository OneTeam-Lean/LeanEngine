package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import lombok.Getter;

@Getter
public class Job extends Container {
  private String jobId;
  private List<Object> tasks;

  public Job(String name, String jobId, List<Object> tasks) {
    super(name);
    this.jobId = jobId;
    this.tasks = tasks;
  }
}
