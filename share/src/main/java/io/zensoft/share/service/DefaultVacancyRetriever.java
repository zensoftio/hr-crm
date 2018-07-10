package io.zensoft.share.service;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import org.springframework.stereotype.Service;

@Service
public class DefaultVacancyRetriever implements VacancyRetriever {

    @Override
    public VacancyResponse getInfo(Vacancy vacancy) {
        return null;
    }
}
