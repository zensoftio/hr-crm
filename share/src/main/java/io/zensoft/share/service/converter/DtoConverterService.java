package io.zensoft.share.service.converter;


/**
 * Created by temirlan on 7/3/18.
 */
public interface DtoConverterService<MODEL, DTO> {
    DTO toDto(MODEL model);

    MODEL fromDto(DTO dto);
}
