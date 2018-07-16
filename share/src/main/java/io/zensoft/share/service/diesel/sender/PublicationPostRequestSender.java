package io.zensoft.share.service.diesel.sender;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.diesel.RequestResponse;
import io.zensoft.share.model.diesel.RequestStatus;
import io.zensoft.share.service.diesel.RequestSender;
import io.zensoft.share.service.diesel.body.PublicationPostRequestBody;
import io.zensoft.share.service.diesel.body.PublicationVacancyContentPreparer;
import io.zensoft.share.service.diesel.header.PublicationHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:diesel.properties")
public class PublicationPostRequestSender implements RequestSender {
    @Value( "${diesel.publication.url}" )
    private String serverUrlPostRequestReceiver;

    private PublicationHeader publicationHeader;
    private PublicationPostRequestBody publicationPostRequestBody;
    private PublicationVacancyContentPreparer publicationVacancyContentPreparer;
    private HttpStatus statusCode;

    @Autowired
    public PublicationPostRequestSender(PublicationHeader publicationHeader,
                                        PublicationPostRequestBody publicationPostRequestBody,
                                        PublicationVacancyContentPreparer publicationVacancyContentPreparer) {
        this.publicationHeader = publicationHeader;
        this.publicationPostRequestBody = publicationPostRequestBody;
        this.publicationVacancyContentPreparer = publicationVacancyContentPreparer;
    }

    public RequestResponse sendPostRequestForPublication(RestTemplate restTemplate, Vacancy vacancy, String sessionId, String authKey) {
        HttpEntity<?> request = new HttpEntity<>(publicationPostRequestBody.createBodyOfRequestInMap(vacancy, sessionId, authKey), publicationHeader.getPublicationHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
        statusCode = response.getStatusCode();
        RequestResponse requestResponse = new RequestResponse();
        return getFilledResponseFromSender(requestResponse);
    }

    public void addHeaderCookie(String sessionId){
        publicationHeader.addCookieToHeaders(sessionId);
    }

    @Override
    public RequestResponse getFilledResponseFromSender(RequestResponse requestResponse) {
        if (statusCode.equals(HttpStatus.FOUND)) {
            requestResponse.setStatus(RequestStatus.SUCCESS);
            return requestResponse;
        }
        requestResponse.setStatus(RequestStatus.FAILED);
        return requestResponse;
    }
}
