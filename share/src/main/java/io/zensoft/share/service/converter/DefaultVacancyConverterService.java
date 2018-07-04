package io.zensoft.share.service.converter;

import io.zensoft.share.dto.VacancyDto;
import io.zensoft.share.model.Vacancy;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 7/3/18.
 */
@Service
public class DefaultVacancyConverterService implements DtoConverterService<Vacancy, VacancyDto> {

    @Override
    public VacancyDto toDto(Vacancy vacancy) {
        return null;
    }

    @Override
    public Vacancy fromDto(VacancyDto vacancyDto) {
        return null;
    }
}
