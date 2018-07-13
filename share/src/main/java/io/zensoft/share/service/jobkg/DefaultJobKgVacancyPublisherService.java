package io.zensoft.share.service.jobkg;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.service.jobkg.auth.AuthorizationFailedException;
import io.zensoft.share.service.jobkg.auth.JobKgAuthorizationService;
import io.zensoft.share.service.jobkg.builder.JobKgVacancyPublicationContentBuilderService;
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
            String cookie = authorizationService.authorize();
            MultiValueMap<String, String> content = contentBuilderService.build(vacancy);
            publish(cookie, content);
        } catch (AuthorizationFailedException e) {
            throw new PublicationFailedException("Could not authorize");
        } catch (HttpServerErrorException e) {
            //publishes but throws internal 500 server error
            //will be logged
        } catch (Exception e) {
            throw new PublicationFailedException("An error occurred during publishing");
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
        restTemplate.exchange(serverUrl, HttpMethod.POST, entity, String.class);
    }
}
