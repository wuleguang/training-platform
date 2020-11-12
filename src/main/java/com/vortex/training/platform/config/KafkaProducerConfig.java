package com.vortex.training.platform.config;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import java.util.Map;

/**
 * @author : light
 * @date: 2020/11/10 14:14
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Autowired
    private KafkaEnvConfig springKafkaEnvConfig;

    /**
     * 创建一个kafka管理类，相当于rabbitMQ的管理类rabbitAdmin,没有此bean无法自定义的使用adminClient创建topic
     * @return KafkaAdmin
     */
    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = Maps.newHashMap();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, springKafkaEnvConfig.getBootstrapServers());
        return new KafkaAdmin(configs);
    }

    /**
     * 创建topic
     * @return NewTopic
     */
    @Bean
    public NewTopic topic() {
        NewTopic newTopic = TopicBuilder
                .name(springKafkaEnvConfig.getTopic().getName())
                .partitions(springKafkaEnvConfig.getTopic().getPartitions())
                .replicas(springKafkaEnvConfig.getTopic().getReplicas())
                .build();
        return newTopic;
    }


    /**
     * 生产者模板
     * @return KafkaTemplate
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * 生产者工厂
     * @return ProducerFactory
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    /**
     * 生产者配置
     * @return Map
     * {@link:https://kafka.apache.org/25/documentation.html#producerconfigs}
     */
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> producerConfigs = Maps.newHashMap();
        //kafka服务端地址
        producerConfigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                springKafkaEnvConfig.getBootstrapServers());
        //序列化方式，七种，最常用的是String
        producerConfigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerConfigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //producer端的消息确认机制,-1和all都表示消息不仅要写入本地的leader中还要写入对应的副本中
        producerConfigs.put(ProducerConfig.ACKS_CONFIG, springKafkaEnvConfig.getProducer().getAcks());
        //缓冲区数据的最大值
        producerConfigs.put(ProducerConfig.BUFFER_MEMORY_CONFIG, springKafkaEnvConfig.getProducer().getBufferMemory());
        //设置消息发送失败重试次数
        producerConfigs.put(ProducerConfig.RETRIES_CONFIG, springKafkaEnvConfig.getProducer().getRetries());
        //设置消息批处理大小，较小的批处理大小将使批处理不那么常见，并可能降低吞吐量(批处理大小为零将完全禁用批处理)。
        // 非常大的批处理可能会更加浪费内存，因为我们总是会分配指定批处理大小的缓冲区，以预期会有更多的记录。
        producerConfigs.put(ProducerConfig.BATCH_SIZE_CONFIG, springKafkaEnvConfig.getProducer().getBatchSize());
        //client.id,生产端的标志
        producerConfigs.put(ProducerConfig.CLIENT_ID_CONFIG, springKafkaEnvConfig.getProducer().getClientId());
        //压缩方式none, gzip, snappy, lz4, zstd，这里设置为none，消息比较小
        producerConfigs.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,
                springKafkaEnvConfig.getProducer().getCompressionType());
        //延时时间，延时时间到达之后计算批量发送的大小没达到也发送消息
        //可以较小请求服务端的次数，提高吞吐量。
        producerConfigs.put(ProducerConfig.LINGER_MS_CONFIG,
                springKafkaEnvConfig.getProducer().getLingerMs());
        //设置broker响应时间，如果broker在60秒之内还是没有返回给producer确认消息，则认为发送失败
        producerConfigs.put(ProducerConfig.MAX_BLOCK_MS_CONFIG,
                springKafkaEnvConfig.getProducer().getRequestTimeout());
        //添加生产者发送消息的拦截器
        producerConfigs.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
                springKafkaEnvConfig.getProducer().getInterceptorClasses());
        return producerConfigs;
    }
}
