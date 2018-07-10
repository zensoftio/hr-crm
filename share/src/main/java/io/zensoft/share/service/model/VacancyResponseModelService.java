package io.zensoft.share.service.model;

import io.zensoft.share.model.PublisherServiceType;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by temirlan on 6/29/18.
 */
@Service
public interface VacancyResponseModelService extends ModelRepositoryService<VacancyResponse, Long> {
    List<VacancyResponse> getAllByVacancy(Vacancy vacancy);

    VacancyResponse getByVacancyAndPublisherServiceType(Vacancy vacancy, PublisherServiceType publisherServiceType);
}
