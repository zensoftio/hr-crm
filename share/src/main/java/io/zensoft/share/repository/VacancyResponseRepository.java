package io.zensoft.share.repository;

import io.zensoft.share.model.VacancyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyResponseRepository extends JpaRepository<VacancyResponse, Long> {

}