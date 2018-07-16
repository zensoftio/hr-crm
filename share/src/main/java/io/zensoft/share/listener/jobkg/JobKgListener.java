package io.zensoft.share.listener.jobkg;

import io.zensoft.share.config.rabbitmq.RabbitMqComponentDeclarationConfiguration;
import io.zensoft.share.dto.VacancyDto;
import io.zensoft.share.listener.VacancyListener;
import io.zensoft.share.service.PublisherManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * Created by temirlan on 7/13/18.
 */
@Slf4j
@Controller
public class JobKgListener implements VacancyListener {
    @Autowired
    @Qualifier("jobKgPublisherManagerService")
    private PublisherManagerService publisherManagerService;

    @Override
    @RabbitListener(queues = RabbitMqComponentDeclarationConfiguration.QUEUE_JOB_KG_PUBLISH)
    public void publish(VacancyDto vacancyDto) {
        log.info("Received request from monolith to publish vacancy with body " + vacancyDto);
        publisherManagerService.publish(vacancyDto);
        log.info("Request from monolith to publish vacancy with body " + vacancyDto + " handled");
    }

    @Override
    @RabbitListener(queues = RabbitMqComponentDeclarationConfiguration.QUEUE_JOB_KG_GET_INFO)
    public void getInfo(VacancyDto vacancyDto) {
        log.info("Received request from monolith to get info about published vacancy with body " + vacancyDto);
        publisherManagerService.getInfo(vacancyDto);
        log.info("Request from monolith to get info about vacancy with body " + vacancyDto + " handled");
    }
}
