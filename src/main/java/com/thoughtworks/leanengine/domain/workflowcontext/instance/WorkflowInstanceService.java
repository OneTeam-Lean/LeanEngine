package com.thoughtworks.leanengine.domain.workflowcontext.instance;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowInstanceContextPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowInstanceContextRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkflowInstanceService {
  private final WorkflowInstanceContextRepository workflowInstanceContextRepository;

  public WorkflowInstanceService(
      WorkflowInstanceContextRepository workflowInstanceContextRepository) {
    this.workflowInstanceContextRepository = workflowInstanceContextRepository;
  }

  public void runInstance(Workflow workflow) {
    WorkflowInstance workflowInstance = WorkflowInstance.of(workflow);
    workflowInstance.execute();
    workflowInstanceContextRepository.save(
        WorkflowInstanceContextPO.of(
            workflow.getId(), workflowInstance.getWorkflowInstanceContext()));
  }
}
