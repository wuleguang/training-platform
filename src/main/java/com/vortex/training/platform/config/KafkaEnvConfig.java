package com.vortex.training.platform.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : light
 * @date: 2020/11/10 14:32
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaEnvConfig {
    private String bootstrapServers;

    private Topic topic;

    private Producer producer;

    private Consumer consumer;

    /**
     * producer 消息发送者配置
     */
    @Data
    public static class Producer {
        private String acks;
        private Long bufferMemory;
        private Integer batchSize;
        private Integer retries;
        private String compressionType;
        private String clientId;
        private Long lingerMs;
        private Long requestTimeout;
        private String interceptorClasses;
    }

    /**
     * consumer 消息消费者配置
     */
    @Data
    public static class Consumer {
        private String groupId;
        private Integer fetchMinSize;
        private Integer fetchMaxWait;
        private Integer heartbeatInterval;
        private Boolean enableAutoCommit;
        private String autoOffsetReset;
        private Integer maxPollRecords;
        private String clientId;
    }

    /**
     * topic相关配置
     */
    @Data
    public static class Topic {
        private String name;
        private Integer partitions;
        private Integer replicas;
    }
}
