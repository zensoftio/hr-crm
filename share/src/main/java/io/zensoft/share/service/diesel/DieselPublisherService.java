package io.zensoft.share.service.diesel;

import io.zensoft.share.model.PublisherServiceType;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.model.VacancyStatus;
import io.zensoft.share.service.PublisherService;
import io.zensoft.share.service.diesel.authkey.AuthKeyGetRequestSender;
import io.zensoft.share.service.diesel.login.LoginPostRequestSender;
import io.zensoft.share.service.diesel.publication.PublicationPostRequestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Service
public class DieselPublisherService implements PublisherService {
    private RestTemplate restTemplate;
    private LoginPostRequestSender loginPostRequestSender;
    private PublicationPostRequestSender publicationPostRequestSender;
    private AuthKeyGetRequestSender authKeyGetRequestSender;

    private String sessionId;
    private String authKey;

    @Autowired
    public DieselPublisherService(LoginPostRequestSender loginPostRequestSender,
                                  PublicationPostRequestSender publicationPostRequestSender,
                                  AuthKeyGetRequestSender authKeyGetRequestSender) {
        restTemplate = new RestTemplate();
        this.loginPostRequestSender = loginPostRequestSender;
        this.publicationPostRequestSender = publicationPostRequestSender;
        this.authKeyGetRequestSender = authKeyGetRequestSender;
        restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    @Override
    public VacancyResponse publish(Vacancy vacancy) {
        VacancyResponse vacancyResponse = new VacancyResponse();
        vacancyResponse.setVacancy(vacancy);
        vacancyResponse.setPublisherServiceType(PublisherServiceType.DIESEL_ELCAT_KG);

        sessionId = loginPostRequestSender.sendPostRequestForLogin(restTemplate);
        if(loginPostRequestSender.getFilledResponseFromSender(vacancyResponse).getStatus().equals(VacancyStatus.FAILED)){
            return vacancyResponse;
        }

        authKeyGetRequestSender.addHeaderCookie(sessionId);
        authKey = authKeyGetRequestSender.sendGetRequestToGetResponseWithAuthKey(restTemplate);
        if(authKeyGetRequestSender.getFilledResponseFromSender(vacancyResponse).getStatus().equals(VacancyStatus.FAILED)){
            return vacancyResponse;
        }

        publicationPostRequestSender.addHeaderCookie(sessionId);
        publicationPostRequestSender.sendPostRequestForPublication(restTemplate, vacancy, sessionId, authKey);
        if(publicationPostRequestSender.getFilledResponseFromSender(vacancyResponse).getStatus().equals(VacancyStatus.FAILED)){
            return vacancyResponse;
        }
        if(vacancyResponse.getStatus().equals(VacancyStatus.SUCCESS)){
            vacancyResponse.setMessage("Vacancy posted successfully, everything is ok.");
            vacancyResponse.setStatus(VacancyStatus.SUCCESS);
        }
        return vacancyResponse;
    }
}
