package com.vortex.training.platform.mq.kafka;

import com.alibaba.fastjson.JSONObject;
import com.vortex.training.platform.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author : light
 * @date: 2020/11/10 16:23
 */
@Component
@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 异步非阻塞发送消息
     * @param topic 主题
     * @param object 消息内容
     */
    public void sendToKafkaAsync(String topic, Object object) {
        final ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, JSONObject.toJSONString(object));
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);
        future.addCallback(result -> {
            log.info("消息发送成功", JSONObject.toJSONString(object));
        }, (KafkaFailureCallback<String, String>) ex -> {
                ProducerRecord<String, String> failed = ex.getFailedProducerRecord();
                log.error("消息发送至kafka失败：message:{}", JSONObject.toJSONString(failed));
            });
    }


    /**
     * 同步阻塞发送消息
     * @param topic 主题
     * @param object 消息内容
     */
    public void sendToKafkasync(String topic, Object object) {
        final ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, JSONObject.toJSONString(object));
        try {
            kafkaTemplate.send(record).get(Constants.NUM_TEN, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
