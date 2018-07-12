package io.zensoft.share.service.diesel.publication;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.diesel.RequestStatus;
import io.zensoft.share.model.diesel.RequestsResponse;
import io.zensoft.share.service.diesel.RequestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PublicationPostRequestSender implements RequestSender {
    @Value( "${diesel.publication.url}" )
    private String serverUrlPostRequestReceiver;

    private PublicationHeaders publicationHeaders;
    private PublicationPostRequestsForm publicationPostRequestsForm;
    private PublicationVacancyContentPreparer publicationVacancyContentPreparer;
    private String statusCode;

    @Autowired
    public PublicationPostRequestSender(PublicationHeaders publicationHeaders,
                                        PublicationPostRequestsForm publicationPostRequestsForm,
                                        PublicationVacancyContentPreparer publicationVacancyContentPreparer) {
        this.publicationHeaders = publicationHeaders;
        this.publicationPostRequestsForm = publicationPostRequestsForm;
        this.publicationVacancyContentPreparer = publicationVacancyContentPreparer;
    }

    public RequestsResponse sendPostRequestForPublication(RestTemplate restTemplate, Vacancy vacancy, String sessionId, String authKey) {
        HttpEntity<?> request = new HttpEntity<>(publicationPostRequestsForm.createBodyOfRequestInMap(vacancy, sessionId, authKey), publicationHeaders.getPublicationHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
        statusCode = response.getStatusCode().toString();
        RequestsResponse requestsResponse = new RequestsResponse();
        return getFilledResponseFromSender(requestsResponse);
    }

    public void addHeaderCookie(String sessionId){
        publicationHeaders.addCookieToHeaders(sessionId);
    }

    @Override
    public RequestsResponse getFilledResponseFromSender(RequestsResponse requestsResponse) {
        if(!statusCode.equals("302")){
            requestsResponse.setStatus(RequestStatus.FAILED);
        }
        if(statusCode.equals("302")){
            requestsResponse.setStatus(RequestStatus.SUCCESS);
        }
        return requestsResponse;
    }
}
