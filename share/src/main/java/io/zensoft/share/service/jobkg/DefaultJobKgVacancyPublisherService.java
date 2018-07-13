package io.zensoft.share.service.jobkg;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.service.jobkg.auth.AuthorizationFailedException;
import io.zensoft.share.service.jobkg.auth.JobKgAuthorizationService;
import io.zensoft.share.service.jobkg.builder.JobKgVacancyPublicationContentBuilderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * Created by temirlan on 7/12/18.
 */
@Service
@Slf4j
public class DefaultJobKgVacancyPublisherService implements JobKgVacancyPublisherService {
    private final JobKgAuthorizationService authorizationService;
    private final JobKgVacancyPublicationContentBuilderService contentBuilderService;

    @Autowired
    public DefaultJobKgVacancyPublisherService(JobKgAuthorizationService authorizationService,
                                               JobKgVacancyPublicationContentBuilderService contentBuilderService) {
        this.authorizationService = authorizationService;
        this.contentBuilderService = contentBuilderService;
    }

    @Override
    public void publish(Vacancy vacancy) throws PublicationFailedException {
        try {
            log.info("Calling authorize() method");
            String cookie = authorizationService.authorize();
            log.info("Successfully authorized");
            log.info("Preparing content for publication");
            MultiValueMap<String, String> content = contentBuilderService.build(vacancy);
            log.info("Content for publication was prepared");
            publish(cookie, content);
        } catch (AuthorizationFailedException e) {
            throw new PublicationFailedException(e.getMessage());
        } catch (HttpServerErrorException e) {
            //publishes but throws internal 500 server error
            log.info("Vacancy published but " + e.getClass().getName() + " exception was thrown");
        } catch (Exception e) {
            log.error("Could not publish vacancy");
            throw new PublicationFailedException("An error occurred when publishing. Message : " + e.getMessage());
        }
    }

    private void publish(String cookie, MultiValueMap<String, String> content) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Cookie", cookie);
        HttpEntity entity = new HttpEntity(content, headers);
        String serverUrl = "http://www.job.kg/cabinet/vacancy/create";
        log.info("Sending post request to publish");
        ResponseEntity response = restTemplate.exchange(serverUrl, HttpMethod.POST, entity, String.class);
        log.info("Request to publish vacancy was sent");
        log.info("Request to publish vacancy received response with status code " + response.getStatusCode());

    }
}
