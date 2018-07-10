package io.zensoft.share.service;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import org.springframework.stereotype.Service;

public interface PublisherService {

    /**
     * the method is responsible for publish
     * @param vacancy the object given by {@link }
     * @return {@link io.zensoft.share.model.VacancyResponse} object created during process
     */
    VacancyResponse publish(Vacancy vacancy);
}