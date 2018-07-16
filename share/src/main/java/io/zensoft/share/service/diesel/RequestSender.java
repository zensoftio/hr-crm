package io.zensoft.share.service.diesel;

import io.zensoft.share.model.diesel.RequestResponse;

public interface RequestSender {
    RequestResponse getFilledResponseFromSender(RequestResponse requestResponse);
}
