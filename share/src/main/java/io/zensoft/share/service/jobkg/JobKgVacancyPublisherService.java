package io.zensoft.share.service.jobkg;

import io.zensoft.share.model.Vacancy;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 7/12/18.
 */
@Service
public interface JobKgVacancyPublisherService {
    void publish(Vacancy vacancy) throws PublicationFailedException;
}
