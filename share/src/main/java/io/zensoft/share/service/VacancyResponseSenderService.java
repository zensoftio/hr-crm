package io.zensoft.share.service;

import io.zensoft.share.config.rabbitmq.RabbitMqComponentDeclarationConfiguration;
import io.zensoft.share.dto.VacancyResponseDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 7/3/18.
 */
@Service
public class VacancyResponseSenderService implements ResponseSenderService<VacancyResponseDto> {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public VacancyResponseSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //real queue name or exchange has to be injected
    @Override
    public void respond(VacancyResponseDto vacancyResponseDto) {
        rabbitTemplate.convertAndSend(RabbitMqComponentDeclarationConfiguration.TOPIC_SHARE, "share.response",
                vacancyResponseDto);
    }
}
