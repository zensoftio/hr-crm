package io.zensoft.share.service.facebook;

import io.zensoft.share.dto.VacancyDto;
import io.zensoft.share.dto.VacancyResponseDto;
import io.zensoft.share.model.PublisherServiceType;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.service.PublisherManagerService;
import io.zensoft.share.service.PublisherService;
import io.zensoft.share.service.VacancyResponseSenderService;
import io.zensoft.share.service.VacancyRetrieverService;
import io.zensoft.share.service.converter.DtoConverterService;
import io.zensoft.share.service.model.VacancyModelService;
import io.zensoft.share.service.model.VacancyResponseModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FacebookPublisherManagerService implements PublisherManagerService {

    private final DtoConverterService<Vacancy,VacancyDto> defaultVacancyConverterService;
    private final DtoConverterService<VacancyResponse, VacancyResponseDto> defaultVacancyResponseConverterService;
    private final VacancyModelService defaultVacancyModelService;
    private final VacancyResponseModelService defaultVacancyResponseModelService;
    private final PublisherService facebookPublisherService;
    private final VacancyRetrieverService defaultVacancyRetrieverService;
    private final VacancyResponseSenderService vacancyResponseSenderService;

    @Autowired
    public FacebookPublisherManagerService(DtoConverterService<Vacancy, VacancyDto> defaultVacancyConverterService,
                                           DtoConverterService<VacancyResponse, VacancyResponseDto> defaultVacancyResponseConverterService,
                                           VacancyModelService defaultVacancyModelService,
                                           VacancyResponseModelService defaultVacancyResponseModelService,
                                           PublisherService facebookPublisherService,
                                           VacancyRetrieverService defaultVacancyRetrieverService,
                                           VacancyResponseSenderService vacancyResponseSenderService) {
        this.defaultVacancyConverterService = defaultVacancyConverterService;
        this.defaultVacancyResponseConverterService = defaultVacancyResponseConverterService;
        this.defaultVacancyModelService = defaultVacancyModelService;
        this.defaultVacancyResponseModelService = defaultVacancyResponseModelService;
        this.facebookPublisherService = facebookPublisherService;
        this.defaultVacancyRetrieverService = defaultVacancyRetrieverService;
        this.vacancyResponseSenderService = vacancyResponseSenderService;
    }

    @Override
    public void publish(VacancyDto vacancyDto) {
        log.info("processing publish request from listener");
        Vacancy vacancy = convertToVacancyAndSaveToDatabase(vacancyDto);
        VacancyResponse vacancyResponse = facebookPublisherService.publish(vacancy);
        defaultVacancyResponseModelService.save(vacancyResponse);
        convertToDtoAndRespond(vacancyResponse);
    }

    @Override
    public void getInfo(VacancyDto vacancyDto) {
        log.info("processing get info request from listener");
        Vacancy vacancy = convertToVacancyAndSaveToDatabase(vacancyDto);
        VacancyResponse vacancyResponse = defaultVacancyRetrieverService.getInfo(vacancy, PublisherServiceType.FACEBOOK);
        convertToDtoAndRespond(vacancyResponse);
    }

    private Vacancy convertToVacancyAndSaveToDatabase(VacancyDto vacancyDto) {
        Vacancy vacancy = defaultVacancyConverterService.fromDto(vacancyDto);
        defaultVacancyModelService.save(vacancy);
        return  vacancy;
    }

    private void convertToDtoAndRespond (VacancyResponse vacancyResponse) {
        VacancyResponseDto vacancyResponseDto = defaultVacancyResponseConverterService.toDto(vacancyResponse);
        vacancyResponseSenderService.respond(vacancyResponseDto);
        log.info("response of publishing vacancy is sent to monolith");
    }
}
