package io.zensoft.share.service.diesel.authkey;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthKeyGetRequestSender {
    @Value("${diesel.authKey.url}")
    private String serverUrlPostRequestReceiver;
    private AuthKeyHeaders authKeyHeaders;

    @Autowired
    public AuthKeyGetRequestSender(AuthKeyHeaders authKeyHeaders) {
        this.authKeyHeaders = authKeyHeaders;
    }

    public String sendGetRequestToGetResponseWithAuthKey(RestTemplate restTemplate) {
        HttpEntity<?> request = new HttpEntity<>(authKeyHeaders.getAuthKeyGetterHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
        return getAuthKeyFromHtmlAsStringResponse(response.getBody());
    }

    private String getAuthKeyFromHtmlAsStringResponse(String htmlBody) {
        Document document = Jsoup.parse(htmlBody);
        Element authKeyInput = document.select("input[name=auth_key]").first();
        String authKey = authKeyInput.attr("value");
        return authKey;
    }

    public void addHeaderCookie(String sessionId) {
        authKeyHeaders.addCookieToHeaders(sessionId);
    }

}
