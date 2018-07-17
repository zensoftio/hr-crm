package io.zensoft.share.service.diesel;

import io.zensoft.share.model.PublisherServiceType;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.model.VacancyStatus;
import io.zensoft.share.service.PublisherService;
import io.zensoft.share.service.diesel.sender.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Date;

@Service
@Slf4j
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
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    @Override
    public VacancyResponse publish(Vacancy vacancy) {
        log.info("publish given Vacancy in diesel.elcat.kg");
        log.info("create VacancyResponse and fill with static values");
        VacancyResponse vacancyResponse = new VacancyResponse();
        vacancyResponse.setVacancy(vacancy);
        vacancyResponse.setStatus(VacancyStatus.PENDING);
        vacancyResponse.setPublisherServiceType(PublisherServiceType.DIESEL_ELCAT_KG);
        vacancyResponse.setPublishDate(new Date());

        if (activateLoginPostRequestSender(vacancyResponse).getStatus().name().equals("FAILED")) {
            log.info("return VacancyResponse because Login Failed");
            return vacancyResponse;
        }

        if (activateAuthKeyGetRequestSender(vacancyResponse,
                loginPostRequestSender).getStatus().name().equals("FAILED")) {
            log.info("return VacancyResponse because authKeyRetriever Failed");
            return vacancyResponse;
        }

        if (activatePublicationPostRequestSender(vacancyResponse,
                loginPostRequestSender, vacancy).getStatus().name().equals("FAILED")) {
            log.info("return VacancyResponse because publication Failed");
            return vacancyResponse;
        }
        log.info("fill VacancyResponse with positiv values and return it");
        vacancyResponse.setStatus(VacancyStatus.SUCCESS);
        vacancyResponse.setMessage("Vacancy posted successfully, everything is ok.");
        log.info("Vacancy posted successfully, everything is ok.");
        return vacancyResponse;
    }

    private VacancyResponse activateLoginPostRequestSender(VacancyResponse vacancyResponse) {
        log.info("run login method and save response's status");
        String loginStatus;
        loginStatus = loginPostRequestSender.sendPostRequestForLogin(restTemplate).getStatus().name();
        if (loginStatus.equals("FAILED")) {
            log.info("set values in VacancyReponse if RequestResponse's status is FAILED");
            vacancyResponse.setStatus(VacancyStatus.FAILED);
            vacancyResponse.setMessage("Post request for login is failed.");
        }
        log.info("return filled VacancyResponse");
        return vacancyResponse;
    }

    private VacancyResponse activateAuthKeyGetRequestSender(VacancyResponse vacancyResponse,
                                                            LoginPostRequestSender loginPostRequestSender) {
        log.info("run authKeySender method and save response's status");
        String statusAuthKey;
        authKeyGetRequestSender.addHeaderCookie(loginPostRequestSender.getSessionId());
        statusAuthKey = authKeyGetRequestSender.sendGetRequestToGetResponseWithAuthKey(restTemplate).getStatus().name();
        if (statusAuthKey.equals("FAILED")) {
            log.info("set values in VacancyReponse if RequestResponse's status is FAILED");
            vacancyResponse.setStatus(VacancyStatus.FAILED);
            vacancyResponse.setMessage("Get request for authKey executing is failed.");
        }
        log.info("return filled VacancyResponse");
        return vacancyResponse;
    }

    private VacancyResponse activatePublicationPostRequestSender(VacancyResponse vacancyResponse,
                                                                 LoginPostRequestSender loginPostRequestSender,
                                                                 Vacancy vacancy) {
        log.info("run publication method and save response's status");
        String statusPublication;
        publicationPostRequestSender.addHeaderCookie(loginPostRequestSender.getSessionId());
        statusPublication = publicationPostRequestSender.sendPostRequestForPublication(
                restTemplate,
                vacancy,
                loginPostRequestSender.getSessionId(),
                authKeyGetRequestSender.getAuthKey()
        ).getStatus().name();

        if (statusPublication.equals("FAILED")) {
            log.info("set values in VacancyReponse if RequestResponse's status is FAILED");
            vacancyResponse.setStatus(VacancyStatus.FAILED);
            vacancyResponse.setMessage("Post request for publication is failed.");
        }
        log.info("return filled VacancyResponse");
        return vacancyResponse;
    }
}
