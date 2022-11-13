package com.microservices.demo.kafka.admin.exception;

/**
 * @PROJECT microservices
 * @Author Elimane on 09/11/2022
 */
public class KafkaClientException extends RuntimeException{
  public KafkaClientException() {
  }

  public KafkaClientException(String message) {
    super(message);
  }

  public KafkaClientException(String message, Throwable cause) {
    super(message, cause);
  }
}
