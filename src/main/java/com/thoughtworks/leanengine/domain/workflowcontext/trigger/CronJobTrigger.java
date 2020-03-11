package com.thoughtworks.leanengine.domain.workflowcontext.trigger;

import com.thoughtworks.leanengine.domain.workflowcontext.workflow.Workflow;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
    try {
      log.info("Start to trigger batch workflow to execute.");

      List<Workflow> workflows = workflowService.queryAllWorkflow();
      if (CollectionUtils.isEmpty(workflows)) {
        return;
      }

      workflows
          .stream()
          .filter(w -> w.isLastExecutionCompleted())
          .forEach(
              w -> {
                log.info("Triggered {} workflow to execute.", w.getId());
                w.execute();
              });
      log.info("Finished to trigger batch workflow to execute.");
    } catch (Exception ex) {
      log.info("Error on trigger batch workflow to execute. {}", ex.getMessage());
    }
  }
}
