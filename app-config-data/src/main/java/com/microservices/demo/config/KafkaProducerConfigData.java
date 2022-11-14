package com.microservices.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @PROJECT microservices
 * @Author Elimane on 13/11/2022
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-producer-config")
public class KafkaProducerConfigData {
  private String keySerializerClass;
  private String valueSerializerClass;
  private String compressionType;
  private String acks;
  private Integer batchSize;
  private Integer batchSizeBoostFactor;
}
