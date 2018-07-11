package io.zensoft.share.repository;

import io.zensoft.share.model.PublisherServiceType;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyResponseRepository extends JpaRepository<VacancyResponse, Long> {

    List<VacancyResponse> getAllByVacancy(Vacancy vacancy);

    VacancyResponse getByVacancyAndPublisherServiceType(Vacancy vacancy, PublisherServiceType publisherServiceType);
}