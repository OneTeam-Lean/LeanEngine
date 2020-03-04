package com.thoughtworks.leanengine.domain.workflowcontext.instance;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowInstanceContextPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowInstanceRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkflowInstanceService {
  private final WorkflowInstanceRepository workflowInstanceRepository;

  public WorkflowInstanceService(WorkflowInstanceRepository workflowInstanceRepository) {
    this.workflowInstanceRepository = workflowInstanceRepository;
  }

  public void runInstance(Workflow workflow) {
    WorkflowInstance workflowInstance = WorkflowInstance.of(workflow);
    workflowInstance.execute();
    workflowInstanceRepository.save(
        WorkflowInstanceContextPO.of(
            workflow.getId(), workflowInstance.getWorkflowInstanceContext()));
  }
}
