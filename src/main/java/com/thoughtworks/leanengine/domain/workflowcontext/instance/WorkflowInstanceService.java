package com.thoughtworks.leanengine.domain.workflowcontext.instance;

import com.thoughtworks.leanengine.domain.workflowcontext.containers.Workflow;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowNotFoundException;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowInstanceContextPO;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowInstanceContextRepository;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class WorkflowInstanceService {
  private final WorkflowInstanceContextRepository workflowInstanceContextRepository;
  private final WorkflowRepository workflowRepository;

  public WorkflowInstanceService(
      WorkflowInstanceContextRepository workflowInstanceContextRepository,
      WorkflowRepository workflowRepository) {
    this.workflowInstanceContextRepository = workflowInstanceContextRepository;
    this.workflowRepository = workflowRepository;
  }

  public void runInstance(Workflow workflow) {
    WorkflowInstance workflowInstance = WorkflowInstance.of(workflow);
    workflowInstance.execute();
    workflowInstanceContextRepository.save(
        WorkflowInstanceContextPO.of(
            workflow.getId(), workflowInstance.getWorkflowInstanceContext()));
  }

  public boolean runInstance(String workflowId) {
    Optional<WorkflowPO> optionalWorkflowPO = workflowRepository.findById(workflowId);
    if (!optionalWorkflowPO.isPresent()) {
      throw new WorkflowNotFoundException();
    }
    Workflow workflow = optionalWorkflowPO.get().toDomainModel();
    runInstance(workflow);
    return true;
  }
}
