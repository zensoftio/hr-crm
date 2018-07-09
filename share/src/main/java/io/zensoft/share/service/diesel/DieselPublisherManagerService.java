package io.zensoft.share.service.diesel;

import io.zensoft.share.dto.VacancyDto;
import io.zensoft.share.dto.VacancyResponseDto;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.service.PublisherManagerService;
import io.zensoft.share.service.PublisherService;
import io.zensoft.share.service.VacancyResponseSenderService;
import io.zensoft.share.service.converter.DefaultVacancyConverterService;
import io.zensoft.share.service.converter.DefaultVacancyResponseConverterService;
import io.zensoft.share.service.model.impl.DefaultVacancyModelService;
import io.zensoft.share.service.model.impl.DefaultVacancyResponseModelService;
import org.springframework.stereotype.Service;

@Service
public class DieselPublisherManagerService implements PublisherManagerService {

    private final DefaultVacancyConverterService defaultVacancyConverterService;
    private final DefaultVacancyResponseConverterService defaultVacancyResponseConverterService;
    private final DefaultVacancyModelService defaultVacancyModelService;
    private final DefaultVacancyResponseModelService defaultVacancyResponseModelService;
    private final PublisherService dieselPublisherService;
    private final VacancyResponseSenderService vacancyResponseSenderService;

    public DieselPublisherManagerService(DefaultVacancyConverterService defaultVacancyConverterService,
                                         DefaultVacancyResponseConverterService defaultVacancyResponseConverterService,
                                         DefaultVacancyModelService defaultVacancyModelService,
                                         DefaultVacancyResponseModelService defaultVacancyResponseModelService,
                                         PublisherService dieselPublisherService,
                                         VacancyResponseSenderService vacancyResponseSenderService) {
        this.defaultVacancyConverterService = defaultVacancyConverterService;
        this.defaultVacancyResponseConverterService = defaultVacancyResponseConverterService;
        this.defaultVacancyModelService = defaultVacancyModelService;
        this.defaultVacancyResponseModelService = defaultVacancyResponseModelService;
        this.dieselPublisherService = dieselPublisherService;
        this.vacancyResponseSenderService = vacancyResponseSenderService;
    }

    @Override
    public void publish(VacancyDto vacancyDto) {
        Vacancy vacancy = defaultVacancyConverterService.fromDto(vacancyDto);
        defaultVacancyModelService.save(vacancy);
        VacancyResponse vacancyResponse = dieselPublisherService.publish(vacancy);
        defaultVacancyResponseModelService.save(vacancyResponse);
        VacancyResponseDto vacancyResponseDto = defaultVacancyResponseConverterService.toDto(vacancyResponse);
        vacancyResponseSenderService.respond(vacancyResponseDto);
    }

    @Override
    public void getInfo(VacancyDto vacancyDto) {

    }
}
