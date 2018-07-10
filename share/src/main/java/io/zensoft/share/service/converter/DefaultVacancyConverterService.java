package io.zensoft.share.service.converter;

import io.zensoft.share.dto.VacancyDto;
import io.zensoft.share.model.Requirement;
import io.zensoft.share.model.Vacancy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        Vacancy vacancy = new Vacancy();
        vacancy.setUid(vacancyDto.getUid());
        vacancy.setTitle(vacancyDto.getTitle());
        vacancy.setCity(vacancyDto.getCity());
        vacancy.setAddress(vacancyDto.getAddress());
        vacancy.setPosition(vacancyDto.getPosition());
        vacancy.setCount(vacancyDto.getCount());
        vacancy.setWorkingConditions(vacancyDto.getWorkingConditions());
        vacancy.setExperience(vacancyDto.getExperience());
        vacancy.setWorkingHours(vacancyDto.getWorkingHours());
        vacancy.setEmploymentType(vacancyDto.getEmploymentType());
        vacancy.setSalaryMin(vacancyDto.getSalaryMin());
        vacancy.setSalaryMax(vacancyDto.getSalaryMax());
        vacancy.setImage(vacancyDto.getImage());

        List<Requirement> requirements = new ArrayList<>();

        //python complicated here
        vacancyDto.getRequirements().stream().forEach(requirementDto -> {
            Requirement requirement = new Requirement();
            requirement.setName(requirementDto.getName());
            requirement.setType(requirementDto.getType());
            requirements.add(requirement);
        });
        vacancy.setRequirements(requirements);
        return vacancy;
    }
}
