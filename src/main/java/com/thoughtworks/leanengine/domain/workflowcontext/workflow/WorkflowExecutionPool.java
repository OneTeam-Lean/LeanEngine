package com.thoughtworks.leanengine.domain.workflowcontext.workflow;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WorkflowExecutionPool {

  private final WorkflowRepository workflowRepository;
  private final ExecutorService executorService;
  private final List<String> runningList = new CopyOnWriteArrayList<>();

  public WorkflowExecutionPool(WorkflowRepository workflowRepository) {
    this.workflowRepository = workflowRepository;
    this.executorService = Executors.newFixedThreadPool(10);
  }

  public void executeWorkflow(Workflow workflow) {
    executorService.execute(
        () -> {
          if (runningList.stream().anyMatch(ret -> Objects.equals(ret, workflow.getId()))) {
            return;
          }
          runningList.add(workflow.getId());
          while (!Status.isCompletedStatus(workflow.executeByStep())) {
            log.info("workflow name:{} is executeByStep", workflow.getName());
            workflowRepository.save(WorkflowPO.of(workflow));
          }
          workflowRepository.save(WorkflowPO.of(workflow));
          log.info("workflow name:{} execute completed.", workflow.getName());
          runningList.remove(workflow.getId());
        });
  }
}
