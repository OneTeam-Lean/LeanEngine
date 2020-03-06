package com.thoughtworks.leanengine.domain.workflowcontext.trigger;

import com.thoughtworks.leanengine.domain.workflowcontext.workflow.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CronJobTrigger implements WorkflowTrigger {

  private final WorkflowService workflowService;

  public CronJobTrigger(WorkflowService workflowService) {
    this.workflowService = workflowService;
  }

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
              w.execute();
            });
    log.info("Finished to trigger batch workflow to execute.", LocalDateTime.now());
  }
}
