package com.microservices.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @PROJECT microservices
 * @Author Elimane on 08/11/2022
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "twitter-to-kafka-service")
public class TwitterKafkaServiceConfigData {
  private List<String> twitterKeywords;
  private String welcomeMessage;
  private Boolean enableMockTweets;
  private Long mockSleepMs;
  private Integer mockMinTweetLength;
  private Integer mockMaxTweetLength;
}
