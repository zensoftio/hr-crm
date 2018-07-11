package io.zensoft.share.service.diesel.publication;

import io.zensoft.share.model.Vacancy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PublicationPostRequestSender {
    @Value( "${diesel.publication.url}" )
    private String serverUrlPostRequestReceiver;

    private PublicationHeaders publicationHeaders;
    private PublicationPostRequestsForm publicationPostRequestsForm;
    private PublicationVacancyContentPreparer publicationVacancyContentPreparer;

    @Autowired
    public PublicationPostRequestSender(PublicationHeaders publicationHeaders,
                                        PublicationPostRequestsForm publicationPostRequestsForm,
                                        PublicationVacancyContentPreparer publicationVacancyContentPreparer) {
        this.publicationHeaders = publicationHeaders;
        this.publicationPostRequestsForm = publicationPostRequestsForm;
        this.publicationVacancyContentPreparer = publicationVacancyContentPreparer;
    }

    public void sendPostRequestForPublication(RestTemplate restTemplate, Vacancy vacancy, String sessionId, String authKey) {
        HttpEntity<?> request = new HttpEntity<>(publicationPostRequestsForm.createBodyOfRequestInMap(vacancy, sessionId, authKey), publicationHeaders.getPublicationHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
    }

    public void addHeaderCookie(String sessionId){
        publicationHeaders.addCookieToHeaders(sessionId);
    }

}
