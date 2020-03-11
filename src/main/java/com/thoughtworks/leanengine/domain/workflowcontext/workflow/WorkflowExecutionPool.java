package com.thoughtworks.leanengine.domain.workflowcontext.workflow;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WorkflowExecutionPool {

  private final WorkflowRepository workflowRepository;
  private final ExecutorService executorService;

  public WorkflowExecutionPool(WorkflowRepository workflowRepository) {
    this.workflowRepository = workflowRepository;
    this.executorService = Executors.newFixedThreadPool(10);
  }

  public void executeWorkflow(Workflow workflow) {
    executorService.execute(
        () -> {
          while (!Status.isCompletedStatus(workflow.executeByStep())) {
            log.info("workflow name:{} is executeByStep", workflow.getName());
            workflowRepository.save(WorkflowPO.of(workflow));
          }
          log.info("workflow name:{} execute completed.", workflow.getName());
        });
  }
}
