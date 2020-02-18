package com.thoughtworks.leanengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan
public class LeanEngineApplication {

  public static void main(String[] args) {
    SpringApplication.run(LeanEngineApplication.class, args);
  }
}
