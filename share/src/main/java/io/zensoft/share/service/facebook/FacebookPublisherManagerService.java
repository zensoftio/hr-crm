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
import io.zensoft.share.service.converter.DefaultVacancyConverterService;
import io.zensoft.share.service.converter.DefaultVacancyResponseConverterService;
import io.zensoft.share.service.model.impl.DefaultVacancyModelService;
import io.zensoft.share.service.model.impl.DefaultVacancyResponseModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacebookPublisherManagerService implements PublisherManagerService {

    private final DefaultVacancyConverterService vacancyConverterService;
    private final DefaultVacancyResponseConverterService vacancyResponseConverterService;
    private final DefaultVacancyModelService vacancyModelService;
    private final DefaultVacancyResponseModelService vacancyResponseModelService;
    private final PublisherService facebookPublisherService;
    private final VacancyRetrieverService defaultVacancyRetrieverService;
    private final VacancyResponseSenderService vacancyResponseSenderService;

    @Autowired
    public FacebookPublisherManagerService(DefaultVacancyConverterService vacancyConverterService,
                                           DefaultVacancyResponseConverterService vacancyResponseConverterService,
                                           DefaultVacancyModelService vacancyModelService,
                                           DefaultVacancyResponseModelService vacancyResponseModelService,
                                           PublisherService facebookPublisherService,
                                           VacancyRetrieverService defaultVacancyRetrieverService,
                                           VacancyResponseSenderService vacancyResponseSenderService) {
        this.vacancyConverterService = vacancyConverterService;
        this.vacancyResponseConverterService = vacancyResponseConverterService;
        this.vacancyModelService = vacancyModelService;
        this.vacancyResponseModelService = vacancyResponseModelService;
        this.facebookPublisherService = facebookPublisherService;
        this.defaultVacancyRetrieverService = defaultVacancyRetrieverService;
        this.vacancyResponseSenderService = vacancyResponseSenderService;
    }

    @Override
    public void publish(VacancyDto vacancyDto) {
        Vacancy vacancy = convertToVacancyAndSaveToDatabase(vacancyDto);
        VacancyResponse vacancyResponse = facebookPublisherService.publish(vacancy);
        vacancyResponseModelService.save(vacancyResponse);
        convertToDtoAndRespond(vacancyResponse);
    }

    @Override
    public void getInfo(VacancyDto vacancyDto) {
        Vacancy vacancy = convertToVacancyAndSaveToDatabase(vacancyDto);
        VacancyResponse vacancyResponse = defaultVacancyRetrieverService.getInfo(vacancy, PublisherServiceType.FACEBOOK);
        convertToDtoAndRespond(vacancyResponse);
    }

    private Vacancy convertToVacancyAndSaveToDatabase(VacancyDto vacancyDto) {
        Vacancy vacancy = vacancyConverterService.fromDto(vacancyDto);
        vacancyModelService.save(vacancy);
        return  vacancy;
    }

    private void convertToDtoAndRespond (VacancyResponse vacancyResponse) {
        VacancyResponseDto vacancyResponseDto = vacancyResponseConverterService.toDto(vacancyResponse);
        vacancyResponseSenderService.respond(vacancyResponseDto);
    }
}
