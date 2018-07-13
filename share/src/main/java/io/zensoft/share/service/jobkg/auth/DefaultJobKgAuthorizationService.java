package io.zensoft.share.service.jobkg.auth;

import io.zensoft.share.config.jobkg.JobKgUserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by temirlan on 7/12/18.
 */
@Service
@Slf4j
public class DefaultJobKgAuthorizationService implements JobKgAuthorizationService {

    private final JobKgUserProperties jobKgUserProperties;
    private final String authorizationUrl = "http://www.job.kg/login";


    @Autowired
    public DefaultJobKgAuthorizationService(JobKgUserProperties jobKgUserProperties) {
        this.jobKgUserProperties = jobKgUserProperties;
    }

    @Override
    public String authorize(JobKgUserProperties user) throws AuthorizationFailedException {
        try {
            log.info("authorize method called");
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> body = new LinkedMultiValueMap();
            body.add("LoginForm[login]", user.getUsername());
            body.add("LoginForm[password]", user.getPassword());
            body.add("LoginForm[returnUrl]", "http://www.job.kg");
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
            log.info("Sending post request to authorize...");
            ResponseEntity<String> response = restTemplate.exchange(authorizationUrl, HttpMethod.POST, entity, String.class);
            log.info("Post request to authorize was sent");
            log.info("Post request with status " + response.getStatusCode() + " was received");
            String cookie = response.getHeaders().get("Set-Cookie").get(1);
            cookie = cookie.split(";")[0];
            if (response.getStatusCode() != HttpStatus.FOUND) {
                throw new Exception(response.getStatusCode().toString());
            }
            return cookie;
        } catch (Exception e) {
            log.error("Could not authorize to job.kg ");
            log.error("Error message " + e.getMessage());
            throw new AuthorizationFailedException("Failed to authorize! Message : " + e.getMessage());
        }
    }

    @Override
    public String authorize() throws AuthorizationFailedException {
        return authorize(jobKgUserProperties);
    }
}
