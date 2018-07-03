package io.zensoft.share.service.converter;

import io.zensoft.share.dto.VacancyDto;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.builder.VacancyBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 7/3/18.
 */
@Service
public class DefaultVacancyConverterService implements DtoConverterService<Vacancy,VacancyDto> {

    @Override
    public VacancyDto toDto(Vacancy vacancy) {
        return null;
    }

    @Override
    public Vacancy fromDto(VacancyDto vacancyDto) {
        VacancyBuilder vacancyBuilder = new VacancyBuilder();
        vacancyBuilder.
                setVacancyId(vacancyDto.getId()).
                setTitle(vacancyDto.getTitle()).
                setSphere(vacancyDto.getSphere()).
                setDescription(vacancyDto.getDescription()).
                setCompetencies(vacancyDto.getCompetencies()).
                setRequirements(vacancyDto.getRequirements()).
                setResponsibilities(vacancyDto.getResponsibilities()).
                setWorkingConditions(vacancyDto.getWorkingConditions()).
                setSalaryMin(vacancyDto.getSalaryMin()).
                setSalaryMax(vacancyDto.getSalaryMax()).
                setAdditionalInfo(vacancyDto.getAdditionalInfo()).
                setLinks(vacancyDto.getLinks()).
                setImageUrl(vacancyDto.getImageUrl()).
                setEducation(vacancyDto.getEducation()).
                setSchedule(vacancyDto.getSchedule()).
                setCity(vacancyDto.getCity());
        return vacancyBuilder.build();
    }
}
