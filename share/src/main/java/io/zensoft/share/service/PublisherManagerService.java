package io.zensoft.share.service;

import io.zensoft.share.dto.VacancyDto;
import org.springframework.stereotype.Service;

@Service
public interface PublisherManagerService {

    /**
     * basic responsibilities of the method are processes that must be done before
     * and after publishing data
     * <ul>
     *     <li>convert {@link io.zensoft.share.dto.VacancyDto} to {@link io.zensoft.share.model.Vacancy} model
     *     <li>save model to DB
     *     <li>call publish() method, return {@link io.zensoft.share.model.VacancyResponse} as result
     *     <li>save response to DB result from previous operation
     *     <li>respond back to monolith
     * </ul>
     * <p>
     * @param vacancyDto the object given by listener
     */
    void publish(VacancyDto vacancyDto);

    /**
     * basic responsibilities of the method are processes that must be done before
     * responding to monolith
     * <ul>
     *     <li>convert {@link io.zensoft.share.dto.VacancyDto} to {@link io.zensoft.share.model.Vacancy} model
     *     <li>retrieve from DB entity
     *     <li>call getInfo() method, return {@link io.zensoft.share.model.VacancyResponse} as result
     *     <li>convert to response object to {@link io.zensoft.share.dto.VacancyResponseDto}
     *     <li>respond back to monolith
     * </ul>
     * <p>
     * @param vacancyDto the object given by listener
     */
    void getInfo(VacancyDto vacancyDto);
}
