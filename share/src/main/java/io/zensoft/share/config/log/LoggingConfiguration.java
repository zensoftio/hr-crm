package io.zensoft.share.config.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by temirlan on 6/28/18.
 */
@Configuration
public class LoggingConfiguration {

    @Bean
    public Logger rabbitMqMessageListenerLogger() {
        return LoggerFactory.getLogger("RabbitMq message listener logger");
    }

}
