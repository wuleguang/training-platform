package com.vortex.training.platform.mq.kafka;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author : light
 * @date: 2020/11/10 17:19
 */
public class KafkaProducerInterceptor  implements ProducerInterceptor<String, String> {
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {

        return new ProducerRecord<String, String>(record.topic(), record.partition(),
                System.currentTimeMillis(), record.key(), record.value());
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
         //该方法会在消息被应答之前或消息发送失败的时候调用，也可以在此做重试操作


    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
