package io.zensoft.share.service.jobkg.builder;

import io.zensoft.share.model.Vacancy;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

/**
 * Created by temirlan on 7/12/18.
 */
@Service
public interface JobKgVacancyPublicationContentBuilderService {
    MultiValueMap<String, String> build(Vacancy vacancy);
}
