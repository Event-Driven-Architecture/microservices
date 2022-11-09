package com.microservices.demo.common.config;

import com.microservices.demo.config.RetryConfigData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.Sleeper;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @PROJECT microservices
 * @Author Elimane on 09/11/2022
 */

@Configuration
public class RetryConfig {

  private final RetryConfigData retryConfigData;

  public RetryConfig(RetryConfigData configData) {
    this.retryConfigData = configData;
  }

  @Bean
  public RetryTemplate retryTemplate(){
    RetryTemplate retryTemplate = new RetryTemplate();

    // Increase wait time for each retry attempt
    ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
    exponentialBackOffPolicy.setInitialInterval(retryConfigData.getInitialIntervalMs());
    exponentialBackOffPolicy.setMaxInterval(retryConfigData.getMaxIntervalMs());
    exponentialBackOffPolicy.setMultiplier(retryConfigData.getMultiplier());

    retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);

    SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
    simpleRetryPolicy.setMaxAttempts(retryConfigData.getMaxAttempts());

    retryTemplate.setRetryPolicy(simpleRetryPolicy);

    return retryTemplate;
  }

}
