package io.zensoft.share.service.jobkg;

import io.zensoft.share.model.PublisherServiceType;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.model.VacancyStatus;
import io.zensoft.share.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by temirlan on 7/12/18.
 */
@Service
@Slf4j
public class JobKgPublisherService implements PublisherService {

    private final JobKgVacancyPublisherService vacancyPublisherService;

    @Autowired
    public JobKgPublisherService(JobKgVacancyPublisherService vacancyPublisherService) {
        this.vacancyPublisherService = vacancyPublisherService;
    }

    @Override
    public VacancyResponse publish(Vacancy vacancy) {
        log.info("Received request for publishing to job.kg ");
        VacancyResponse vacancyResponse = new VacancyResponse();
        vacancyResponse.setVacancy(vacancy);
        vacancyResponse.setPublisherServiceType(PublisherServiceType.JOB_KG);
        vacancyResponse.setPublishDate(new Date());
        try {
            vacancyPublisherService.publish(vacancy);
            vacancyResponse.setStatus(VacancyStatus.SUCCESS);
            vacancyResponse.setMessage("Successfully published");
            vacancyResponse.setUrl("http://www.job.kg/cabinet/vacancy?status=everything");
        } catch (PublicationFailedException e) {
            vacancyResponse.setStatus(VacancyStatus.FAILED);
            vacancyResponse.setMessage(e.getMessage());
        }
        log.info("Returning VacancyResponse with body " + vacancyResponse);
        return vacancyResponse;
    }

}
