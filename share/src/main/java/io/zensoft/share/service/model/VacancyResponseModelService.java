package io.zensoft.share.service.model;

import io.zensoft.share.model.PublisherServiceType;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;

import java.util.List;

/**
 * Created by temirlan on 6/29/18.
 */
public interface VacancyResponseModelService extends ModelRepositoryService<VacancyResponse, Long> {
    List<VacancyResponse> getAllByVacancy(Vacancy vacancy);

    VacancyResponse getByVacancyAndPublisherServiceType(Vacancy vacancy, PublisherServiceType publisherServiceType);

    /**
     * method to retrieve {@link VacancyResponse} by {@link Vacancy#uuid} and {@link PublisherServiceType}
     * @param vacancy {@link Vacancy} object converted from Dto. !!!IMPORTANT, {@code uuid} must not be null or empty
     *                               unless return result will be null.
     *
     * @param publisherServiceType {@link PublisherServiceType} object. Every implementation of
     *                                  {@link io.zensoft.share.service.PublisherManagerService} assigns it manually
     *
     * @return {@link VacancyResponse}
     */
    VacancyResponse getByVacancy_UuidAndPublisherServiceType(Vacancy vacancy,PublisherServiceType publisherServiceType);
}
