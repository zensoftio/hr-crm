package io.zensoft.share.service.converter;

import io.zensoft.share.dto.DepartmentDto;
import io.zensoft.share.dto.RequirementDto;
import io.zensoft.share.model.Requirement;
import io.zensoft.share.model.RequirementType;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 7/11/18.
 */
@Service
public class DefaultRequirementConverterService implements DtoConverterService<Requirement, RequirementDto> {
    @Override
    public RequirementDto toDto(Requirement requirement) {
        RequirementDto requirementDto = new RequirementDto();
        requirementDto.setName(requirement.getName());
        requirementDto.setType(requirement.getType().name());
        requirementDto.setDepartment(new DepartmentDto(0, requirement.getName()));
        return requirementDto;
    }

    @Override
    public Requirement fromDto(RequirementDto requirementDto) {
        Requirement requirement = new Requirement();
        requirement.setName(requirementDto.getName());
        requirement.setDepartment(requirementDto.getDepartment().getName());
        requirement.setType(RequirementType.getByString(requirementDto.getType()));
        return requirement;
    }
}
