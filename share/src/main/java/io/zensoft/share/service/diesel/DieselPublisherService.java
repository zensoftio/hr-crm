package io.zensoft.share.service.diesel;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.service.PublisherService;
import io.zensoft.share.service.diesel.authkey.AuthKeyGetRequestSender;
import io.zensoft.share.service.diesel.login.LoginPostRequestSender;
import io.zensoft.share.service.diesel.publication.PublicationPostRequestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Service
public class DieselPublisherService implements PublisherService {
    private RestTemplate restTemplate;
    @Autowired
    private LoginPostRequestSender loginPostRequestSender;
    @Autowired
    private PublicationPostRequestSender publicationPostRequestSender;
    @Autowired
    private AuthKeyGetRequestSender authKeyGetRequestSender;

    private String sessionId;
    private String authKey;

    public DieselPublisherService() {
        restTemplate = new RestTemplate();
    }

    @Override
    public VacancyResponse publish(Vacancy vacancy) {
        restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));
        VacancyResponse vacancyResponse = new VacancyResponse();
        vacancyResponse.setVacancy(vacancy);

        sessionId = loginPostRequestSender.sendPostRequestForLogin(restTemplate);

        authKeyGetRequestSender.addHeaderCookie(sessionId);

        authKey = authKeyGetRequestSender.sendGetRequestToGetResponseWithAuthKey(restTemplate);

        publicationPostRequestSender.addHeaderCookie(sessionId);

        publicationPostRequestSender.sendPostRequestForPublication(restTemplate, vacancy, sessionId, authKey);
        return vacancyResponse;
    }

    @Override
    public VacancyResponse getInfo(Vacancy vacancy) {
        return null;
    }
}
