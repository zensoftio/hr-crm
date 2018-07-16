package io.zensoft.share.service.diesel;

import io.zensoft.share.dto.VacancyDto;
import io.zensoft.share.dto.VacancyResponseDto;
import io.zensoft.share.model.PublisherServiceType;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.service.PublisherManagerService;
import io.zensoft.share.service.PublisherService;
import io.zensoft.share.service.ResponseSenderService;
import io.zensoft.share.service.VacancyRetrieverService;
import io.zensoft.share.service.converter.DtoConverterService;
import io.zensoft.share.service.model.VacancyModelService;
import io.zensoft.share.service.model.VacancyResponseModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("dieselPublisherManagerService")
public class DieselPublisherManagerService implements PublisherManagerService {
    private final DtoConverterService<Vacancy, VacancyDto> vacancyConverterService;
    private final DtoConverterService<VacancyResponse, VacancyResponseDto> vacancyResponseConverterService;
    private final VacancyModelService vacancyModelService;
    private final VacancyResponseModelService vacancyResponseModelService;
    private final PublisherService dieselPublisherService;
    private final VacancyRetrieverService vacancyRetrieverService;
    private final ResponseSenderService<VacancyResponseDto> vacancyResponseSenderService;

    @Autowired
    public DieselPublisherManagerService(DtoConverterService<Vacancy, VacancyDto> vacancyConverterService,
                                        DtoConverterService<VacancyResponse, VacancyResponseDto> vacancyResponseConverterService,
                                        VacancyModelService vacancyModelService,
                                        VacancyResponseModelService vacancyResponseModelService,
                                        PublisherService dieselPublisherService,
                                        VacancyRetrieverService vacancyRetrieverService,
                                        ResponseSenderService<VacancyResponseDto> vacancyResponseSenderService) {
        this.vacancyConverterService = vacancyConverterService;
        this.vacancyResponseConverterService = vacancyResponseConverterService;
        this.vacancyModelService = vacancyModelService;
        this.vacancyResponseModelService = vacancyResponseModelService;
        this.dieselPublisherService = dieselPublisherService;
        this.vacancyRetrieverService = vacancyRetrieverService;
        this.vacancyResponseSenderService = vacancyResponseSenderService;
    }

    @Override
    public void publish(VacancyDto vacancyDto) {
        Vacancy vacancy = vacancyConverterService.fromDto(vacancyDto);
        vacancyModelService.save(vacancy);
        VacancyResponse vacancyResponse = dieselPublisherService.publish(vacancy);
        vacancyResponseModelService.save(vacancyResponse);
        VacancyResponseDto vacancyResponseDto = vacancyResponseConverterService.toDto(vacancyResponse);
        vacancyResponseSenderService.respond(vacancyResponseDto);
    }

    @Override
    @Transactional
    public void getInfo(VacancyDto vacancyDto) {
        Vacancy vacancy = vacancyConverterService.fromDto(vacancyDto);
        VacancyResponse vacancyResponse = vacancyRetrieverService.getInfo(vacancy, PublisherServiceType.DIESEL_ELCAT_KG);
        VacancyResponseDto vacancyResponseDto = vacancyResponseConverterService.toDto(vacancyResponse);
        vacancyResponseSenderService.respond(vacancyResponseDto);
    }
}
