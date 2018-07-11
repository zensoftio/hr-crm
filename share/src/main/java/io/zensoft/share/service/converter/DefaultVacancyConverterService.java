package io.zensoft.share.service.converter;

import io.zensoft.share.dto.RequirementDto;
import io.zensoft.share.dto.VacancyDto;
import io.zensoft.share.model.Requirement;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.WorkingHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by temirlan on 7/3/18.
 */
@Service
public class DefaultVacancyConverterService implements DtoConverterService<Vacancy, VacancyDto> {

    private final DtoConverterService<Requirement, RequirementDto> requirementConverterService;

    @Autowired
    public DefaultVacancyConverterService(DtoConverterService<Requirement, RequirementDto> requirementConverterService) {
        this.requirementConverterService = requirementConverterService;
    }

    @Override
    public VacancyDto toDto(Vacancy vacancy) {
        VacancyDto vacancyDto = new VacancyDto();
        vacancyDto.setUuid(vacancy.getUuid());
        vacancyDto.setTitle(vacancy.getTitle());
        vacancyDto.setCity(vacancy.getCity());
        vacancyDto.setAddress(vacancy.getAddress());
        vacancyDto.setPosition(vacancy.getPosition());
        vacancyDto.setCount(vacancy.getCount());
        vacancyDto.setWorkConditions(vacancy.getWorkConditions());
        vacancyDto.setWorkingHours(vacancy.getWorkingHours().name());
        vacancyDto.setSalaryMax(vacancy.getSalaryMax());
        vacancyDto.setSalaryMin(vacancy.getSalaryMin());
        vacancyDto.setImage(vacancy.getImage());
        vacancyDto.setResponsibilities(vacancy.getResponsibilities());
        vacancyDto.setComment(vacancy.getComment());
        List<RequirementDto> requirements = new ArrayList<>();
        vacancy.getRequirements().forEach(requirement -> {
            RequirementDto requirementDto = requirementConverterService.toDto(requirement);
            requirements.add(requirementDto);
        });
        vacancyDto.setRequirements(requirements);
        return vacancyDto;
    }

    @Override
    public Vacancy fromDto(VacancyDto vacancyDto) {
        Vacancy vacancy = new Vacancy();
        vacancy.setUuid(vacancyDto.getUuid());
        vacancy.setTitle(vacancyDto.getTitle());
        vacancy.setCity(vacancyDto.getCity());
        vacancy.setAddress(vacancyDto.getAddress());
        vacancy.setPosition(vacancyDto.getPosition());
        vacancy.setCount(vacancyDto.getCount());
        vacancy.setWorkConditions(vacancyDto.getWorkConditions());
        vacancy.setSalaryMin(vacancyDto.getSalaryMin());
        vacancy.setSalaryMax(vacancyDto.getSalaryMax());
        vacancy.setImage(vacancyDto.getImage());
        vacancy.setResponsibilities(vacancyDto.getResponsibilities());
        vacancy.setComment(vacancyDto.getComment());
        vacancy.setWorkingHours(WorkingHours.getByString(vacancyDto.getWorkingHours()));
        List<Requirement> requirements = new ArrayList<>();
        vacancyDto.getRequirements().forEach(requirementDto -> {
            Requirement requirement = requirementConverterService.fromDto(requirementDto);
            requirements.add(requirement);
        });
        vacancy.setRequirements(requirements);
        return vacancy;
    }
}
