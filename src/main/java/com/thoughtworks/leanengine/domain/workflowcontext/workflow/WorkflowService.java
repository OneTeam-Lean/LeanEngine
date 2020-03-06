package com.thoughtworks.leanengine.domain.workflowcontext.workflow;

import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowNotFoundException;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {
  private final WorkflowRepository workflowRepository;

  public WorkflowService(WorkflowRepository workflowRepository) {
    this.workflowRepository = workflowRepository;
  }

  public String saveWorkflow(Workflow workflow) {
    WorkflowPO po = WorkflowPO.of(workflow);
    WorkflowPO workflowPO = workflowRepository.save(po);
    return workflowPO.getId();
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

  public List<Workflow> queryAllWorkflow() {
    return workflowRepository
        .findAll()
        .stream()
        .map(WorkflowPO::toDomainModel)
        .collect(Collectors.toList());
  }

  public void updateWorkflow(Workflow updatedWorkflow) {
    Optional<WorkflowPO> workflowPO = workflowRepository.findById(updatedWorkflow.getId());
    if (!workflowPO.isPresent()) {
      throw new WorkflowNotFoundException();
    }
    Workflow originWorkflow = workflowPO.get().toDomainModel();
    Workflow mergedWorkflow =
        new Workflow(
            originWorkflow.getId(),
            updatedWorkflow.getName(),
            originWorkflow.getLastExecutionStatus(),
            updatedWorkflow.getLanes(),
            updatedWorkflow.getComponents(),
            updatedWorkflow.getDiagrams(),
            originWorkflow.getWorkflowExecutions());
    workflowRepository.save(WorkflowPO.of(mergedWorkflow));
  }
}
