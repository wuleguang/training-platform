package com.vortex.training.platform;

import com.vortex.training.platform.config.KafkaEnvConfig;
import com.vortex.training.platform.mq.kafka.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PlatformApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private KafkaProducer kafkaProducer;

    @Resource
    private KafkaEnvConfig config;

    @Test
    public void sendMessage() {
        kafkaProducer.sendToKafkaAsync(config.getTopic().getName(), "aaaaa");
    }
}
