package com.thoughtworks.leanengine.domain.workflowcontext.workflow;

import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {
  private final WorkflowRepository workflowRepository;

  public WorkflowService(WorkflowRepository workflowRepository) {
    this.workflowRepository = workflowRepository;
  }

  public void saveWorkflow(Workflow workflow) {
    workflowRepository.save(WorkflowPO.of(workflow));
  }

  public Workflow queryWorkflowByName(String name) {
    WorkflowPO workflowPO = workflowRepository.findByName(name);
    if (workflowPO == null) {
      return null;
    }
    return workflowPO.toDomainModel();
  }

  public Workflow queryWorkflowById(String id) {
    Optional<WorkflowPO> workflowPO = workflowRepository.findById(id);
    return workflowPO.map(WorkflowPO::toDomainModel).orElse(null);
  }
}
