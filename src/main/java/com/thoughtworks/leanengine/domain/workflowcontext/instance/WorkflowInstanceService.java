package com.thoughtworks.leanengine.domain.workflowcontext.instance;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkflowInstanceService {
  private final WorkflowRepository workflowRepository; // 应该新加一个 context 的 repo,暂时用这个代替

  public WorkflowInstanceService(WorkflowRepository workflowRepository) {
    this.workflowRepository = workflowRepository;
  }

  public void runInstance(Workflow workflow) {
    WorkflowInstance workflowInstance = WorkflowInstance.of(workflow);
    workflowInstance.execute();
    // workflowRepository.save(workflowInstance.getWorkflowInstanceContext())
  }
}
