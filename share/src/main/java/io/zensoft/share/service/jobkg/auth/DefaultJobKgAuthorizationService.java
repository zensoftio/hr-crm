package io.zensoft.share.service.jobkg.auth;

import io.zensoft.share.model.jobkg.JobKgUser;
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
public class DefaultJobKgAuthorizationService implements JobKgAuthorizationService {

    private final JobKgUser jobKgUser;
    private final String authorizationUrl = "http://www.job.kg/login";


    @Autowired
    public DefaultJobKgAuthorizationService(JobKgUser jobKgUser) {
        this.jobKgUser = jobKgUser;
    }

    @Override
    public String authorize(JobKgUser user) throws AuthorizationFailedException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> body = new LinkedMultiValueMap();
            body.add("LoginForm[login]", user.getUsername());
            body.add("LoginForm[password]", user.getPassword());
            body.add("LoginForm[returnUrl]", "http://www.job.kg");
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(authorizationUrl, HttpMethod.POST, entity, String.class);
            String cookie = response.getHeaders().get("Set-Cookie").get(1);
            cookie = cookie.split(";")[0];
            return cookie;
        } catch (Exception e) {
            throw new AuthorizationFailedException("Failed to authorize! Message : " + e.getMessage());
        }
    }

    @Override
    public String authorize() throws AuthorizationFailedException {
        return authorize(jobKgUser);
    }
}
