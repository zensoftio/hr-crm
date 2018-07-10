package io.zensoft.share.service;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;

public interface VacancyRetriever {
    /**
     * the method is responsible for retrieving publish result of specific service
     * @param vacancy the object given by {@link PublisherManagerService}
     * @return {@link io.zensoft.share.model.VacancyResponse} object retrieved from DB
     */
    VacancyResponse getInfo(Vacancy vacancy);
}
