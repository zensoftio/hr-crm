package io.zensoft.share.service.diesel.authkey;

import io.zensoft.share.model.diesel.RequestStatus;
import io.zensoft.share.model.diesel.RequestsResponse;
import io.zensoft.share.service.diesel.RequestSender;
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
public class AuthKeyGetRequestSender implements RequestSender {
    @Value("${diesel.authKey.url}")
    private String serverUrlPostRequestReceiver;
    private AuthKeyHeaders authKeyHeaders;
    private String statusCode;
    private String authKey;

    @Autowired
    public AuthKeyGetRequestSender(AuthKeyHeaders authKeyHeaders) {
        this.authKeyHeaders = authKeyHeaders;
    }

    public RequestsResponse sendGetRequestToGetResponseWithAuthKey(RestTemplate restTemplate) {
        HttpEntity<?> request = new HttpEntity<>(authKeyHeaders.getAuthKeyGetterHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
        statusCode = response.getStatusCode().toString();
        authKey = getAuthKeyFromHtmlAsStringResponse(response.getBody());
        RequestsResponse requestsResponse = new RequestsResponse();
        return getFilledResponseFromSender(requestsResponse);
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

    @Override
    public RequestsResponse getFilledResponseFromSender(RequestsResponse requestsResponse) {
        if(!statusCode.equals("200")){
            requestsResponse.setStatus(RequestStatus.FAILED);
        }
        if(statusCode.equals("200")){
            requestsResponse.setStatus(RequestStatus.SUCCESS);
        }
        return requestsResponse;
    }

    public String getAuthKey() {
        return authKey;
    }
}
