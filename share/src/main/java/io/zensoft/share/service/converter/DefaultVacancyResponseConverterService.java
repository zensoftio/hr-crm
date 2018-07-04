package io.zensoft.share.service.converter;

import io.zensoft.share.dto.VacancyResponseDto;
import io.zensoft.share.model.VacancyResponse;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 7/3/18.
 */
@Service
public class DefaultVacancyResponseConverterService implements DtoConverterService<VacancyResponse, VacancyResponseDto> {
    @Override
    public VacancyResponseDto toDto(VacancyResponse vacancyResponse) {
        return null;
    }

    @Override
    public VacancyResponse fromDto(VacancyResponseDto vacancyResponseDto) {
        return null;
    }
}
