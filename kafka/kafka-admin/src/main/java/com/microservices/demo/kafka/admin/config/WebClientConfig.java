package com.microservices.demo.kafka.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @PROJECT microservices
 * @Author Elimane on 10/11/2022
 */

@Configuration // Indicates that the class has @Bean methods to create spring bean
public class WebClientConfig {
  // We will inject that webclient object anywhere
  @Bean // Indicate that method produce a bean to be managed by spring container
  public WebClient webClient(){
   return WebClient.builder().build();
  }

}
