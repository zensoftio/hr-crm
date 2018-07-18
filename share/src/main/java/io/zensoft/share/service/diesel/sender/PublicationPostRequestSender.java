package io.zensoft.share.service.diesel.sender;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.diesel.RequestResponse;
import io.zensoft.share.service.diesel.body.PublicationPostRequestBody;
import io.zensoft.share.service.diesel.body.PublicationVacancyContentBuilder;
import io.zensoft.share.service.diesel.header.PublicationHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:diesel.properties")
@Slf4j
public class PublicationPostRequestSender {
    @Value( "${diesel.publication.url}" )
    private String serverUrlPostRequestReceiver;

    private PublicationHeader publicationHeader;
    private PublicationPostRequestBody publicationPostRequestBody;
    private PublicationVacancyContentBuilder publicationVacancyContentBuilder;
    private RequestResponseBuilderService requestResponseBuilderService;

    @Autowired
    public PublicationPostRequestSender(PublicationHeader publicationHeader,
                                        PublicationPostRequestBody publicationPostRequestBody,
                                        PublicationVacancyContentBuilder publicationVacancyContentBuilder,
                                        RequestResponseBuilderService requestResponseBuilderService) {
        this.publicationHeader = publicationHeader;
        this.publicationPostRequestBody = publicationPostRequestBody;
        this.publicationVacancyContentBuilder = publicationVacancyContentBuilder;
        this.requestResponseBuilderService = requestResponseBuilderService;
    }

    public RequestResponse sendPostRequestForPublication(RestTemplate restTemplate, Vacancy vacancy, String sessionId, String authKey) {
        log.info("send POST request to publish");
        HttpEntity<?> request = new HttpEntity<>(publicationPostRequestBody.createBodyOfRequestInMap(vacancy, sessionId, authKey), publicationHeader.getPublicationHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
        RequestResponse requestResponse = new RequestResponse();
        log.info("return filled RequestResponse");
        return requestResponseBuilderService.getFilledResponseFromPostRequestSenderSender(requestResponse, response.getStatusCode());
    }

    public void addHeaderCookie(String sessionId){
        log.info("call method from header class to add header with cookie(sessionId)");
        publicationHeader.addCookieToHeaders(sessionId);
    }

    public void deleteHeaderCookie() {
        log.info("call method from header class to delete header with cookie(sessionId)");
        publicationHeader.deleteCookieFromHeaders();
    }

}
