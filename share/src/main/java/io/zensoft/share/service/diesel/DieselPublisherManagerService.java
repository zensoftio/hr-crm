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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DieselPublisherManagerService implements PublisherManagerService {

    private final DefaultVacancyConverterService vacancyConverterService;
    private final DefaultVacancyResponseConverterService vacancyResponseConverterService;
    private final DefaultVacancyModelService vacancyModelService;
    private final DefaultVacancyResponseModelService vacancyResponseModelService;
    private final PublisherService dieselPublisherService;
    private final VacancyResponseSenderService vacancyResponseSenderService;

    @Autowired
    public DieselPublisherManagerService(DefaultVacancyConverterService vacancyConverterService,
                                         DefaultVacancyResponseConverterService vacancyResponseConverterService,
                                         DefaultVacancyModelService vacancyModelService,
                                         DefaultVacancyResponseModelService vacancyResponseModelService,
                                         PublisherService dieselPublisherService,
                                         VacancyResponseSenderService vacancyResponseSenderService) {
        this.vacancyConverterService = vacancyConverterService;
        this.vacancyResponseConverterService = vacancyResponseConverterService;
        this.vacancyModelService = vacancyModelService;
        this.vacancyResponseModelService = vacancyResponseModelService;
        this.dieselPublisherService = dieselPublisherService;
        this.vacancyResponseSenderService = vacancyResponseSenderService;
    }

    @Override
    @Transactional
    public void publish(VacancyDto vacancyDto) {
        Vacancy vacancy = vacancyConverterService.fromDto(vacancyDto);
        vacancyModelService.save(vacancy);
        VacancyResponse vacancyResponse = dieselPublisherService.publish(vacancy);
        vacancyResponseModelService.save(vacancyResponse);
        VacancyResponseDto vacancyResponseDto = vacancyResponseConverterService.toDto(vacancyResponse);
        vacancyResponseSenderService.respond(vacancyResponseDto);
    }

    @Override
    public void getInfo(VacancyDto vacancyDto) {
    }
}
