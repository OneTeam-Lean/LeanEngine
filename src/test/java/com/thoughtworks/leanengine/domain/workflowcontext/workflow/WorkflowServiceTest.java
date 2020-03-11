package com.thoughtworks.leanengine.domain.workflowcontext.workflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.thoughtworks.leanengine.ApiTestBase;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class WorkflowServiceTest extends ApiTestBase {
  @Autowired private WorkflowService workflowService;
  @Autowired private WorkflowRepository workflowRepository;

  @Test
  void return_same_size_execution_as_Component_when_runWorkflow() {
    WorkflowPO workflowPO = workflowRepository.save(buildWorkflowPO("runTest"));

    WorkflowExecution workflowExecution = workflowService.runWorkflow(workflowPO.getId());

    assertNotNull(workflowExecution);
    assertEquals(
        workflowPO.getComponents().size(), workflowExecution.getComponentExecutions().size());
    WorkflowPO repoPO = workflowRepository.findByName("runTest");
    assertNotNull(repoPO);
    assertEquals(
        repoPO.toDomainModel().getWorkflowExecutions().get(0).getComponentExecutions().size(),
        workflowPO.getComponents().size());
  }

  @Ignore
  @Test
  void return_run_Component_when_runWorkflow() throws InterruptedException {
    WorkflowPO workflowPO = workflowRepository.save(buildWorkflowPO("runStepTest"));

    workflowService.runWorkflowByAsync(workflowPO.getId());
    Thread.sleep(10000L);
    WorkflowPO runTest = workflowRepository.findByName("runStepTest");
    WorkflowExecution workflowExecution = runTest.getWorkflowExecutionPOs().get(0).toDomainModel();

    assertNotNull(workflowExecution);

    assertEquals(
        workflowPO.getComponents().size(), workflowExecution.getComponentExecutions().size());
    WorkflowPO repoPO = workflowRepository.findByName("runStepTest");
    assertNotNull(repoPO);
    assertEquals(
        repoPO.toDomainModel().getWorkflowExecutions().get(0).getComponentExecutions().size(),
        workflowPO.getComponents().size());
  }
}
