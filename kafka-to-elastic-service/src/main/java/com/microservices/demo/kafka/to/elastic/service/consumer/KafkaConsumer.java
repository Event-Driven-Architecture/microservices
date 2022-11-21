package com.microservices.demo.kafka.to.elastic.service.consumer;

import org.apache.avro.specific.SpecificRecordBase;

import java.io.Serializable;
import java.util.List;

/**
 * @PROJECT microservices
 * @Author Elimane on 21/11/2022
 */
public interface KafkaConsumer<K extends Serializable, V extends SpecificRecordBase> {
  public void receive(List<V> messages, List<Integer> keys, List<Integer> partitions, List<Long> offset);
}
