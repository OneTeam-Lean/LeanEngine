package com.thoughtworks.leanengine.infra.conf;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataConfiguration {

  @Bean(name = "entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder, DataSource dataSource) {
    return builder
        .dataSource(dataSource)
        .packages("com.thoughtworks.leanengine")
        .persistenceUnit("com.thoughtworks.leanengine.infra.repo.po.PersistenceObject")
        .build();
  }

  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
