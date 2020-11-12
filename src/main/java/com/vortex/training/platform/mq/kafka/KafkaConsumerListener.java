package com.vortex.training.platform.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;


/**
 * @author : light
 * @date: 2020/11/11 11:38
 */
@Component
@Slf4j
public class KafkaConsumerListener {

    /**
     * 消费消息
     * @param record record
     * @param ack ack
     */
    @KafkaListener(id = "training-platform-consumer", containerFactory = "kafkaListenerContainerFactory",
                    topics = "topic_model", errorHandler = "consumerListenerErrorHandler")
    public void listener(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.info("收到生产者发送的的消息，message：={}", record.toString());
        ack.acknowledge();
    }
}
