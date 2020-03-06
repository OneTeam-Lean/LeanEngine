package com.thoughtworks.leanengine.domain.workflowcontext.trigger;

import com.thoughtworks.leanengine.domain.workflowcontext.execution.WorkflowInstanceService;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@org.springframework.stereotype.Component
@Log4j2
public class CronJobTrigger implements WorkflowTrigger {

  @Autowired private WorkflowService workflowService;

  @Autowired private WorkflowInstanceService instanceService;

  @Scheduled(fixedDelayString = "${cron.job.fixed.delay.seconds:600000}")
  @Override
  public void runJob() {
    log.info("Start to trigger batch workflow to execute.", LocalDateTime.now());

    List<Workflow> workflows = workflowService.queryAllWorkflow();
    workflows
        .stream()
        .filter(w -> w.isLastExecutionCompleted())
        .forEach(
            w -> {
              log.info("Triggered {} workflow to execute.", w.getId());
              instanceService.runInstance(w);
            });
    log.info("Finished to trigger batch workflow to execute.", LocalDateTime.now());
  }
}
