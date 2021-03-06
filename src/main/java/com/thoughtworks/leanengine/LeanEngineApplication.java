package com.thoughtworks.leanengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableMongoRepositories
@EntityScan
@EnableScheduling
public class LeanEngineApplication {

  public static void main(String[] args) {
    SpringApplication.run(LeanEngineApplication.class, args);
  }
}
