package io.zensoft.share.service;

import io.zensoft.share.model.PublisherServiceType;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import org.springframework.stereotype.Service;

@Service
public class DefaultVacancyRetrieverService implements VacancyRetrieverService {

    @Override
    public VacancyResponse getInfo(Vacancy vacancy, PublisherServiceType publisherServiceType) {
        return null;
    }
}
