package io.zensoft.share.service;

import io.zensoft.share.dto.VacancyDto;
import io.zensoft.share.dto.VacancyResponseDto;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.service.converter.DtoConverterService;
import io.zensoft.share.service.model.VacancyModelService;
import io.zensoft.share.service.model.VacancyResponseModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public abstract class BasePublisherService implements PublisherService {
    protected final VacancyResponseModelService defaultVacancyResponseModelService;
    private final VacancyModelService defaultVacancyModelService;
    private final DtoConverterService<Vacancy, VacancyDto> vacancyConverter;
    private final DtoConverterService<VacancyResponse, VacancyResponseDto> vacancyResponseConverter;
    private final ResponseSenderService<VacancyResponseDto> responseSenderService;

    public BasePublisherService(VacancyResponseModelService defaultVacancyResponseModelService,
                                VacancyModelService defaultVacancyModelService,
                                DtoConverterService<Vacancy, VacancyDto> vacancyConverter,
                                DtoConverterService<VacancyResponse, VacancyResponseDto> vacancyResponseConverter,
                                ResponseSenderService<VacancyResponseDto> responseSenderService) {
        this.defaultVacancyResponseModelService = defaultVacancyResponseModelService;
        this.defaultVacancyModelService = defaultVacancyModelService;
        this.vacancyConverter = vacancyConverter;
        this.vacancyResponseConverter = vacancyResponseConverter;
        this.responseSenderService = responseSenderService;
    }

    /**
     * pre-publisher method.
     * before publishing to services it converts
     * dto objects and saves to database
     *
     * @param vacancyDto DTO object received from listener
     */
    @Override
    @Transactional
    public final void publish(VacancyDto vacancyDto) {
        Vacancy vacancy = vacancyConverter.fromDto(vacancyDto);
        defaultVacancyModelService.save(vacancy);

        VacancyResponse vacancyResponse = publish(vacancy);

        defaultVacancyResponseModelService.save(vacancyResponse);
        VacancyResponseDto vacancyResponseDto = vacancyResponseConverter.toDto(vacancyResponse);

        responseSenderService.respond(vacancyResponseDto);
    }


    /**
     * pre process method that before
     * sending response converts dto objects
     *
     * @param vacancyDto
     */
    @Override
    @Transactional
    public final void getInfo(VacancyDto vacancyDto) {
        Vacancy vacancy = vacancyConverter.fromDto(vacancyDto);

        VacancyResponse vacancyResponse = getInfo(vacancy);

        VacancyResponseDto vacancyResponseDto = vacancyResponseConverter.toDto(vacancyResponse);

        responseSenderService.respond(vacancyResponseDto);

    }


    /**
     * Retrieve VacancyResponse object from db
     * for the requested Vacancy.
     * Called from this.publish(VacancyDto).
     *
     * @param vacancy Post object
     * @return VacancyResponse object for requested service
     */
    protected abstract VacancyResponse getInfo(Vacancy vacancy);

    /**
     * Publish to service.
     * Create new VacancyResponse object with
     * requested Vacancy object and return it.
     *
     * @param vacancy Vacancy object taken from request
     * @return VacancyResponse object with updated data
     */
    protected abstract VacancyResponse publish(Vacancy vacancy);

}
