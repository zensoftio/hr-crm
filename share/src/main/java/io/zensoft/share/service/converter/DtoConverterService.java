package io.zensoft.share.service.converter;

import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 7/3/18.
 */
@Service
public interface DtoConverterService<MODEL, DTO> {
    DTO toDto(MODEL model);

    MODEL fromDto(DTO dto);
}
