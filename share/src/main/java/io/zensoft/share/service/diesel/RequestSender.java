package io.zensoft.share.service.diesel;

import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.model.diesel.RequestsResponse;

public interface RequestSender {
    RequestsResponse getFilledResponseFromSender(RequestsResponse requestsResponse);
}
