package com.microservices.demo.kafka.producer.config.service.impl;

import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import com.microservices.demo.kafka.producer.config.service.KafkaProducer;
import org.apache.avro.specific.SpecificRecordBase;

import java.io.Serializable;

/**
 * @PROJECT microservices
 * @Author Elimane on 14/11/2022
 */
public class TwitterKafkaProducer<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<Long, TwitterAvroModel> {

  /***
   * @Description To send message to event store
   * @param topicName
   * @param key
   * @param message
   */
  @Override
  public void send(String topicName, Long key, TwitterAvroModel message) {

  }
}
