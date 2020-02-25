package com.thoughtworks.leanengine.infra.conf;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfiguration {

  @Bean
  public Mapper dozerBeanMapper() {
    return new DozerBeanMapper();
  }
}
