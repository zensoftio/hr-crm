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
}
