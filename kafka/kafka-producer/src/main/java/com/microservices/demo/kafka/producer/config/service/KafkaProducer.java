package com.microservices.demo.kafka.producer.config.service;

import org.apache.avro.specific.SpecificRecordBase;

import java.io.Serializable;

/**
 * @PROJECT microservices
 * @Author Elimane on 14/11/2022
 */

/***
 * @Description Used Spring kafka to write kafka producer implementation
 * @param <K>
 * @param <V>
 */
public interface KafkaProducer<K extends Serializable, V extends SpecificRecordBase> {
  void send(String topicName, K key, V message);
}
