package io.zensoft.share.service.Facebook;

import io.zensoft.share.dto.VacancyDto;
import io.zensoft.share.dto.VacancyResponseDto;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.service.PublisherManagerService;
import io.zensoft.share.service.PublisherService;
import io.zensoft.share.service.VacancyResponseSenderService;
import io.zensoft.share.service.converter.DefaultVacancyConverterService;
import io.zensoft.share.service.converter.DefaultVacancyResponseConverterService;
import io.zensoft.share.service.model.VacancyResponseModelService;
import io.zensoft.share.service.model.impl.DefaultVacancyModelService;
import io.zensoft.share.service.model.impl.DefaultVacancyResponseModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacebookPublisherManagerService implements PublisherManagerService {

    private final DefaultVacancyConverterService defaultVacancyConverterService;
    private final DefaultVacancyResponseConverterService defaultVacancyResponseConverterService;
    private final DefaultVacancyModelService defaultVacancyModelService;
    private final DefaultVacancyResponseModelService defaultVacancyResponseModelService;
    private final PublisherService facebookPublisherService;
    private final VacancyResponseSenderService vacancyResponseSenderService;

    @Autowired
    public FacebookPublisherManagerService(DefaultVacancyConverterService defaultVacancyConverterService,
                                           DefaultVacancyResponseConverterService defaultVacancyResponseConverterService,
                                           DefaultVacancyModelService defaultVacancyModelService,
                                           DefaultVacancyResponseModelService defaultVacancyResponseModelService,
                                           PublisherService facebookPublisherService, VacancyResponseModelService vacancyResponseModelService, VacancyResponseSenderService vacancyResponseSenderService) {
        this.defaultVacancyConverterService = defaultVacancyConverterService;
        this.defaultVacancyResponseConverterService = defaultVacancyResponseConverterService;
        this.defaultVacancyModelService = defaultVacancyModelService;
        this.defaultVacancyResponseModelService = defaultVacancyResponseModelService;
        this.facebookPublisherService = facebookPublisherService;
        this.vacancyResponseSenderService = vacancyResponseSenderService;
    }

    @Override
    public void publish(VacancyDto vacancyDto) {
        Vacancy vacancy = defaultVacancyConverterService.fromDto(vacancyDto);
        defaultVacancyModelService.save(vacancy);
        VacancyResponse vacancyResponse = facebookPublisherService.publish(vacancy);
        defaultVacancyResponseModelService.save(vacancyResponse);
        VacancyResponseDto vacancyResponseDto = defaultVacancyResponseConverterService.toDto(vacancyResponse);
        vacancyResponseSenderService.respond(vacancyResponseDto);
    }

    @Override
    public void getInfo(VacancyDto vacancyDto) {

    }
}
