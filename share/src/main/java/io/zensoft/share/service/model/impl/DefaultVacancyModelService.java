package io.zensoft.share.service.model.impl;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.repository.VacancyRepository;
import io.zensoft.share.service.model.VacancyModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 6/29/18.
 */
@Service
public class DefaultVacancyModelService extends AbstractModelRepositoryServiceImpl<Vacancy, Long> implements VacancyModelService {

    private VacancyRepository vacancyRepository;

    @Autowired
    public DefaultVacancyModelService(JpaRepository<Vacancy, Long> repository,
                                      VacancyRepository vacancyRepository) {
        super(repository);
        this.vacancyRepository = vacancyRepository;
    }

    @Override
    public void save(Vacancy vacancy) {
        // don't do anything if uuid is empty
        if(vacancy.getUuid() == null || vacancy.getUuid().length() == 0)
            return;

        Vacancy tempVacancy = vacancyRepository.getByUuid( vacancy.getUuid() );
        // don't save to DB if vacancy with such uuid exists
        // set retrieved entity to vacancy model object
        if(tempVacancy != null){
            vacancy = tempVacancy;
            return;
        }
        super.save(vacancy);

    }

}