package io.zensoft.share.listener.diesel;

import io.zensoft.share.config.rabbitmq.RabbitMqComponentDeclarationConfiguration;
import io.zensoft.share.dto.VacancyDto;
import io.zensoft.share.listener.VacancyListener;
import io.zensoft.share.service.PublisherManagerService;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Log4j
@Controller
public class DieselListener implements VacancyListener {

    private final PublisherManagerService dieselPublisherManagerService;

    @Autowired
    public DieselListener(PublisherManagerService dieselPublisherManagerService) {
        this.dieselPublisherManagerService = dieselPublisherManagerService;
    }


    @Override
    @RabbitListener(queues = RabbitMqComponentDeclarationConfiguration.QUEUE_DIESEL_PUBLISH)
    public void publish(VacancyDto vacancyDto) {
        dieselPublisherManagerService.publish(vacancyDto);
    }

    @Override
    @RabbitListener(queues = RabbitMqComponentDeclarationConfiguration.QUEUE_DIESEL_GET_INFO)
    public void getInfo(VacancyDto vacancyDto) {
        dieselPublisherManagerService.getInfo(vacancyDto);
    }
}
