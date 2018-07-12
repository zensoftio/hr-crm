package io.zensoft.share.service.diesel;

import io.zensoft.share.model.VacancyResponse;

public interface RequestSender {
    VacancyResponse getFilledResponseFromSender(VacancyResponse vacancyResponse);
}
