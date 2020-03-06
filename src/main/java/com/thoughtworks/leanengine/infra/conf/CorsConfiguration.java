package com.thoughtworks.leanengine.infra.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/workflow")
            .allowedOrigins(
                "http://http://ec2-161-189-97-207.cn-northwest-1.compute.amazonaws.com.cn:9003",
                "localhost:8080");
      }
    };
  }
}
