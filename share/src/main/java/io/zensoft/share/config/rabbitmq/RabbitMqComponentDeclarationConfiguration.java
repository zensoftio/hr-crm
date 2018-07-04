package io.zensoft.share.config.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqComponentDeclarationConfiguration {

    public static final String TOPIC_SHARE = "share";
    public static final String QUEUE_FACEBOOK_PUBLISH = "facebookPublish";
    public static final String QUEUE_FACEBOOK_GET_INFO = "facebookGetInfo";
    public static final String QUEUE_DIESEL_PUBLISH = "dieselPublish";
    public static final String QUEUE_DIESEL_GET_INFO = "dieselGetInfo";
    public static final String QUEUE_JOB_KG_PUBLISH = "jobKgPublish";
    public static final String QUEUE_JOB_KG_GET_INFO = "jobKgGetInfo";

    @Bean
    public TopicExchange shareTopicExchange() {
        return new TopicExchange(TOPIC_SHARE);
    }

    @Bean()
    public Queue facebookPublisherQueue() {
        return new Queue(QUEUE_FACEBOOK_PUBLISH);
    }

    @Bean
    public Queue facebookGetInfoQueue() {
        return new Queue(QUEUE_FACEBOOK_GET_INFO);
    }

    @Bean()
    public Queue dieselPublisherQueue() {
        return new Queue(QUEUE_DIESEL_PUBLISH);
    }

    @Bean
    public Queue dieselGetInfoQueue() {
        return new Queue(QUEUE_DIESEL_GET_INFO);
    }

    @Bean
    public Queue jobKgPublisherQueue() {
        return new Queue(QUEUE_JOB_KG_PUBLISH);
    }

    @Bean
    public Queue jobKgGetInfoQueue() {
        return new Queue(QUEUE_JOB_KG_GET_INFO);
    }

    @Bean
    public ObjectMapper snakeCaseObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return objectMapper;
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper snakeCaseObjectMapper) {
        return new Jackson2JsonMessageConverter(snakeCaseObjectMapper);
    }

}
