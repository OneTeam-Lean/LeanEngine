package com.thoughtworks.leanengine.domain.workflowcontext.interfaces;

import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;

public interface Job {
  void execute(WorkflowInstanceContext workflowInstanceContext);
}
