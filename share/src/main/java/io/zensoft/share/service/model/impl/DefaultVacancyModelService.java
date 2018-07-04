package io.zensoft.share.service.model.impl;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.service.model.VacancyModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 6/29/18.
 */
@Service
public class DefaultVacancyModelService extends AbstractModelRepositoryServiceImpl<Vacancy, Long> implements VacancyModelService {
    @Autowired
    public DefaultVacancyModelService(JpaRepository<Vacancy, Long> repository) {
        super(repository);
    }

    @Override
    public void save(Vacancy vacancy) {
        super.save(vacancy);
    }

}