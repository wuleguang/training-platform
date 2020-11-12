package com.vortex.training.platform.config;

import com.google.common.collect.Maps;
import com.vortex.training.platform.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import java.util.Map;

/**
 * @author : light
 * @date: 2020/11/11 10:30
 */
@Configuration
@EnableKafka
@Slf4j
public class KafkaConsumerConfig {


    @Autowired
    private KafkaEnvConfig springKafkaEnvConfig;

    /**
     * 扩展监听器使用(监听容器工厂)
     * @return KafkaListenerContainerFactory
     */
    @Bean(name = "kafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
        kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        //设置并发量，小于或等于Topic的分区数,并且要在consumerFactory设置一次拉取的数量
        factory.setConcurrency(Constants.NUM_ONE);
        //设置拉取等待时间(也可间接的理解为延时消费)
        factory.getContainerProperties().setPollTimeout(Constants.NUM_THREE_THOUSAND);
        //指定使用此bean工厂的监听方法，消费确认为方式为用户指定aks,
        // 可以用下面的配置也可以直接使用enableAutoCommit参数
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }

    /**
     * 批量处理消息
     * @return KafkaListenerContainerFactory
     */
    @Bean(name = "batchFactory")
    public KafkaListenerContainerFactory<?> batchFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);
        return factory;
    }

    /**
     * 消费者工厂
     * @return ConsumerFactory
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    /**
     * 消费者配置
     * @return Map
     */
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> consumerConfigs = Maps.newHashMap();
        //kafka服务端地址
        consumerConfigs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, springKafkaEnvConfig.getBootstrapServers());
        //序列化方式，七种，最常用的是String
        consumerConfigs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfigs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //用于表示该consumer想要加入到哪个group中。默认值是 “”。
        consumerConfigs.put(ConsumerConfig.GROUP_ID_CONFIG, springKafkaEnvConfig.getConsumer().getGroupId());
        //当consumer向一个broker发起fetch请求时，broker返回的records的大小最小值。
        //如果broker中数据量不够的话会wait，直到数据大小满足这个条件。
        consumerConfigs.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG,
                springKafkaEnvConfig.getConsumer().getFetchMinSize());
        //Fetch请求发给broker后，在broker中可能会被阻塞的（当topic中records的总size小于fetch.min.bytes时），
        // 此时这个fetch请求耗时就会比较长。这个配置就是来配置consumer最多等待response多久。
        consumerConfigs.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG,
                springKafkaEnvConfig.getConsumer().getFetchMaxWait());
        //Consumer session 过期时间。这个值必须设置在broker configuration中的
        // group.min.session.timeout.ms 与 group.max.session.timeout.ms之间。
        consumerConfigs.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, Constants.NUM_TEN_THOUSAND);
        //心跳间隔。心跳是在consumer与coordinator之间进行的。心跳是确定consumer存活，加入或者退出group的有效手段。
        // 这个值必须设置的小于session.timeout.ms，因为：当Consumer由于某种原因不能发Heartbeat到coordinator时，
        // 并且时间超过session.timeout.ms时，就会认为该consumer已退出，它所订阅的partition会分配到同一group 内的其它的consumer上。
        consumerConfigs.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,
                springKafkaEnvConfig.getConsumer().getHeartbeatInterval());
        //Consumer 在commit offset时有两种模式：自动提交，手动提交。
        // 手动提交:通过调用commitSync、commitAsync方法的方式完成offset的提交。
        // 自动提交：是Kafka Consumer会在后台周期性的去commit。
        consumerConfigs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
                springKafkaEnvConfig.getConsumer().getEnableAutoCommit());
        //这个配置项，是告诉Kafka Broker在发现kafka在没有初始offset，或者当前的offset是一个不存在的值
        // （如果一个record被删除，就肯定不存在了）时，该如何处理。它有4种处理方式：
        //1） earliest：自动重置到最早的offset。
        //2） latest：看上去重置到最晚的offset。
        //3） none：如果边更早的offset也没有的话，就抛出异常给consumer，
        //    告诉consumer在整个consumer group中都没有发现有这样的offset。
        //如果不是上述3种，只抛出异常给consumer。
        consumerConfigs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                springKafkaEnvConfig.getConsumer().getAutoOffsetReset());
        //Consumer每次调用poll()时取到的records的最大数。
        consumerConfigs.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,
                springKafkaEnvConfig.getConsumer().getMaxPollRecords());
        //Consumer进程的标识。如果设置一个人为可读的值，跟踪问题会比较方便。
        consumerConfigs.put(ConsumerConfig.CLIENT_ID_CONFIG,
                springKafkaEnvConfig.getConsumer().getClientId());
        return consumerConfigs;
    }

    /**
     * 消息消费失败处理
     * @return ConsumerAwareListenerErrorHandler
     */
    @Bean(name = "consumerListenerErrorHandler")
    public ConsumerAwareListenerErrorHandler consumerListenerErrorHandler() {
        return new ConsumerAwareListenerErrorHandler() {
            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
                log.info("消息消费失败，message：{}", message.getPayload().toString());
                return null;
            }
        };
    }
}
