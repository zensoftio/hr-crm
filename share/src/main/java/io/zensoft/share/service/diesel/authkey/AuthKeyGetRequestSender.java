package io.zensoft.share.service.diesel.authkey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthKeyGetRequestSender {
    @Value("${diesel.authKey.url}")
    private String serverUrlPostRequestReceiver;
    private AuthKeyGetterHeaders authKeyGetterHeaders;

    public AuthKeyGetRequestSender() {
        authKeyGetterHeaders = new AuthKeyGetterHeaders();
    }

    public String sendGetRequestToGetResponseWithAuthKey(RestTemplate restTemplate) {
        HttpEntity<?> request = new HttpEntity<>(authKeyGetterHeaders.getAuthKeyGetterHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
        return getAuthKeyFromStringResponse(response.getBody());
    }

    private String getAuthKeyFromStringResponse(String htmlBody) {
        String auth_key = "auth_key' value='";
        String closeTag = "' />";
        String[] subStr1 = htmlBody.split(auth_key);
        String[] strings = subStr1[1].split(closeTag);
        String resultAuthKey = "";

        for (int i = 0; i < 1; i++) {
            resultAuthKey = resultAuthKey + strings[i];
        }
        return resultAuthKey;
    }

    public void addHeaderCookie(String sessionId) {
        authKeyGetterHeaders.addCookieToHeaders(sessionId);
    }

}
