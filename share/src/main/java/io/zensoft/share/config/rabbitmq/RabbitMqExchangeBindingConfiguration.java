package io.zensoft.share.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by temirlan on 6/27/18.
 */
@Configuration
@ComponentScan(basePackageClasses = {RabbitMqComponentDeclarationConfiguration.class})
public class RabbitMqExchangeBindingConfiguration {

    @Autowired
    private TopicExchange shareTopicExchange;

    private final String publish = "publish";
    private final String getInfo = "getInfo";

    public Binding bindQueueToTopicWith(Queue queue, TopicExchange exchange, String with) {
        return BindingBuilder.bind(queue).to(exchange).with(with);
    }

    @Bean
    @Autowired
    public Binding facebookPublishBinding(Queue facebookPublisherQueue) {
        return bindQueueToTopicWith(facebookPublisherQueue, shareTopicExchange, "facebook." + publish);
    }

    @Bean
    @Autowired
    public Binding facebookGetInfoBinding(Queue facebookGetInfoQueue) {
        return bindQueueToTopicWith(facebookGetInfoQueue, shareTopicExchange, "facebook." + getInfo);
    }

    @Bean
    @Autowired
    public Binding dieselPublishBinding(Queue dieselPublisherQueue) {
        return bindQueueToTopicWith(dieselPublisherQueue, shareTopicExchange, "diesel." + publish);
    }

    @Bean
    @Autowired
    public Binding dieselGetInfoBinding(Queue dieselGetInfoQueue) {
        return bindQueueToTopicWith(dieselGetInfoQueue, shareTopicExchange, "diesel." + getInfo);
    }

    @Bean
    @Autowired
    public Binding jobKgPublishBinding(Queue jobKgPublisherQueue) {
        return bindQueueToTopicWith(jobKgPublisherQueue, shareTopicExchange, "jobKg." + publish);
    }

    @Bean
    @Autowired
    public Binding jobKgGetInfoBinding(Queue jobKgGetInfoQueue) {
        return bindQueueToTopicWith(jobKgGetInfoQueue, shareTopicExchange, "jobKg." + getInfo);
    }

}
