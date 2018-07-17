package io.zensoft.share.service.diesel.sender;

import io.zensoft.share.model.diesel.RequestResponse;
import io.zensoft.share.model.diesel.RequestStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RequestResponseBuilderService {

    public RequestResponseBuilderService(){

    }

    public RequestResponse getFilledResponseFromPostRequestSenderSender(RequestResponse requestResponse, HttpStatus statusCode){
        log.info("fill RequestResponse object and return it");
        if (statusCode.equals(HttpStatus.FOUND)) {
            log.info("if responseCode equals 302, POST request sent successfully");
            requestResponse.setStatus(RequestStatus.SUCCESS);
            return requestResponse;
        }
        log.info("if responseCode not equals 302, POST request is failed");
        requestResponse.setStatus(RequestStatus.FAILED);
        return requestResponse;
    }

    public RequestResponse getFilledResponseFromGetRequestSenderSender(RequestResponse requestResponse, HttpStatus statusCode){
        log.info("fill RequestResponse object and return it");
        if (statusCode.equals(HttpStatus.OK)) {
            log.info("if responseCode equals 200, GET request sent successfully");
            requestResponse.setStatus(RequestStatus.SUCCESS);
            return requestResponse;
        }
        log.info("if responseCode not equals 200, GET request is failed");
        requestResponse.setStatus(RequestStatus.FAILED);
        return requestResponse;
    }
}
