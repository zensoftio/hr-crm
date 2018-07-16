package io.zensoft.share.service.diesel;

import io.zensoft.share.model.PublisherServiceType;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.model.VacancyStatus;
import io.zensoft.share.service.PublisherService;
import io.zensoft.share.service.diesel.sender.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Date;

@Service
public class DieselPublisherService implements PublisherService {
    private RestTemplate restTemplate;
    private LoginPostRequestSender loginPostRequestSender;
    private PublicationPostRequestSender publicationPostRequestSender;
    private AuthKeyGetRequestSender authKeyGetRequestSender;

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
        vacancyResponse.setPublishDate(new Date());

        String statusLogin;
        statusLogin = loginPostRequestSender.sendPostRequestForLogin(restTemplate).getStatus().name();
        if(statusLogin.equals("FAILED")){
            vacancyResponse.setStatus(VacancyStatus.FAILED);
            vacancyResponse.setMessage("Post request for login is failed.");
            return vacancyResponse;
        }

        String statusAuthKey;
        authKeyGetRequestSender.addHeaderCookie(loginPostRequestSender.getSessionId());
        statusAuthKey = authKeyGetRequestSender.sendGetRequestToGetResponseWithAuthKey(restTemplate).getStatus().name();
        if(statusAuthKey.equals("FAILED")){
            vacancyResponse.setStatus(VacancyStatus.FAILED);
            vacancyResponse.setMessage("Get request for authKey executing is failed.");
            return vacancyResponse;
        }

        String statusPublication;
        publicationPostRequestSender.addHeaderCookie(loginPostRequestSender.getSessionId());
        statusPublication = publicationPostRequestSender.sendPostRequestForPublication(restTemplate, vacancy, loginPostRequestSender.getSessionId(), authKeyGetRequestSender.getAuthKey()).getStatus().name();
        if(statusPublication.equals("FAILED")){
            vacancyResponse.setStatus(VacancyStatus.FAILED);
            vacancyResponse.setMessage("Post request for publication is failed.");
            return vacancyResponse;
        }
        vacancyResponse.setStatus(VacancyStatus.SUCCESS);
        vacancyResponse.setMessage("Vacancy posted successfully, everything is ok.");
        return vacancyResponse;
    }
}
