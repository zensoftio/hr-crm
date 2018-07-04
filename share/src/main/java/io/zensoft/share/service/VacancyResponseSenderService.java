package io.zensoft.share.service;

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
    //real queue name or exchange has to be injected
    private String exchangeName = "share";

    @Autowired
    public VacancyResponseSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //real queue name or exchange has to be injected
    @Override
    public void respond(VacancyResponseDto vacancyResponseDto) {
        rabbitTemplate.convertAndSend(exchangeName, "facebook.publish", vacancyResponseDto);
    }
}
