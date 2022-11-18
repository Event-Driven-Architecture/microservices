package com.microservices.demo.kafka.producer.config.service.impl;

import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import com.microservices.demo.kafka.producer.config.service.KafkaProducer;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PreDestroy;
import java.io.Serializable;

/**
 * @PROJECT microservices
 * @Author Elimane on 14/11/2022
 */
@Service
public class TwitterKafkaProducer<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<Long, TwitterAvroModel> {

  private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaProducer.class);

  private final KafkaTemplate<Long, TwitterAvroModel> kafkaTemplate;

  public TwitterKafkaProducer(KafkaTemplate<Long, TwitterAvroModel> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  /***
   * @Description To send message to event store
   * @param topicName
   * @param key
   * @param message
   */
  @Override
  public void send(String topicName, Long key, TwitterAvroModel message) {
    LOG.info("Sending message {} to topic {} ", message,topicName);

    // ListenableFuture => is a register callback methods
    // for handling events when the response return
    ListenableFuture<SendResult<Long, TwitterAvroModel>> kafkaResultFuture = kafkaTemplate.send(topicName,key,message);
    addCallBack(topicName, message, kafkaResultFuture);
  }

  /***
   * @PreDestroy To signal that the instance is in the process of being removed by the container
   * @Description Method allowing to destroy each sending before another sending
   */
  @PreDestroy
  public void close(){
      if(kafkaTemplate != null){
        LOG.info("Closing kafka producer");
        kafkaTemplate.destroy();
      }
  }

  /***
   * @Description Allowing to send message to topic
   * @param topicName
   * @param message
   * @param kafkaResultFuture
   */
  private void addCallBack(String topicName, TwitterAvroModel message, ListenableFuture<SendResult<Long, TwitterAvroModel>> kafkaResultFuture) {
    kafkaResultFuture.addCallback(new ListenableFutureCallback<SendResult<Long, TwitterAvroModel>>() {
      @Override
      public void onFailure(Throwable throwable) {
        LOG.error("Error while sending message {} to topic {}", message.toString(), topicName, throwable);
      }

      @Override
      public void onSuccess(SendResult<Long, TwitterAvroModel> result) {
        RecordMetadata recordMetadata = result.getRecordMetadata();
        LOG.debug("Received new metadata. Topic: {}; Partition: {}; Offset: {}; Timestamp: {}, at time {}",
          recordMetadata.topic(),
          recordMetadata.partition(),
          recordMetadata.offset(),
          recordMetadata.timestamp(),
          System.nanoTime()
          );
      }
    });
  }
}
