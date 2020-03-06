package com.thoughtworks.leanengine.infra.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ScheduleConfig {

  @Value("${schedule.task.pool.size:20}")
  private int taskPoolSize = 20;

  @Value("${schedule.thread.name.prefix:CronJobTrigger}")
  private String scheduleThreadNamePrefix = "CronJobTrigger";

  @Bean
  public TaskScheduler poolScheduler() {
    ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
    scheduler.setThreadNamePrefix(scheduleThreadNamePrefix);
    scheduler.setPoolSize(taskPoolSize);
    scheduler.initialize();
    return scheduler;
  }
}
