package io.zensoft.share.service.facebook;

import io.zensoft.share.dto.VacancyDto;
import io.zensoft.share.dto.VacancyResponseDto;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.service.*;
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
    private final DefaultVacancyRetriever vacancyRetriever;
    private final VacancyResponseSenderService vacancyResponseSenderService;

    @Autowired
    public FacebookPublisherManagerService(DefaultVacancyConverterService vacancyConverterService,
                                           DefaultVacancyResponseConverterService vacancyResponseConverterService,
                                           DefaultVacancyModelService vacancyModelService,
                                           DefaultVacancyResponseModelService vacancyResponseModelService,
                                           PublisherService facebookPublisherService,
                                           DefaultVacancyRetriever vacancyRetriever,
                                           VacancyResponseSenderService vacancyResponseSenderService) {
        this.vacancyConverterService = vacancyConverterService;
        this.vacancyResponseConverterService = vacancyResponseConverterService;
        this.vacancyModelService = vacancyModelService;
        this.vacancyResponseModelService = vacancyResponseModelService;
        this.facebookPublisherService = facebookPublisherService;
        this.vacancyRetriever = vacancyRetriever;
        this.vacancyResponseSenderService = vacancyResponseSenderService;
    }

    @Override
    public void publish(VacancyDto vacancyDto) {
        Vacancy vacancy = convertToVacancyAndSaveToDatabase(vacancyDto);
        VacancyResponse vacancyResponse = facebookPublisherService.publish(vacancy);
        saveToDatabaseAndConvertToDtoAndRespond(vacancyResponse);
    }

    @Override
    public void getInfo(VacancyDto vacancyDto) {
        Vacancy vacancy = convertToVacancyAndSaveToDatabase(vacancyDto);
        VacancyResponse vacancyResponse = vacancyRetriever.getInfo(vacancy);
        saveToDatabaseAndConvertToDtoAndRespond(vacancyResponse);
    }

    public Vacancy convertToVacancyAndSaveToDatabase(VacancyDto vacancyDto) {
        Vacancy vacancy = vacancyConverterService.fromDto(vacancyDto);
        vacancyModelService.save(vacancy);
        return  vacancy;
    }

    public void saveToDatabaseAndConvertToDtoAndRespond (VacancyResponse vacancyResponse) {
        vacancyResponseModelService.save(vacancyResponse);
        VacancyResponseDto vacancyResponseDto = vacancyResponseConverterService.toDto(vacancyResponse);
        vacancyResponseSenderService.respond(vacancyResponseDto);
    }
}
