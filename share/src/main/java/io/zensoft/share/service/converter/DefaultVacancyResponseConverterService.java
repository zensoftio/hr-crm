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
        VacancyResponseDto vacancyResponseDto = new VacancyResponseDto();
        vacancyResponseDto.setUid(vacancyResponse.getVacancy().getUid());
        vacancyResponseDto.setMessage(vacancyResponse.getMessage());
        vacancyResponseDto.setUrl(vacancyResponse.getUrl());
        vacancyResponseDto.setStatus(vacancyResponse.getStatus().name());
        vacancyResponseDto.setPublisherServiceType(vacancyResponse.getPublisherServiceType().name());
        vacancyResponseDto.setPublishDate(vacancyResponse.getPublishDate());
        return vacancyResponseDto;
    }

    @Override
    public VacancyResponse fromDto(VacancyResponseDto vacancyResponseDto) {
        return null;
    }
}
