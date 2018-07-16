package io.zensoft.share.service.diesel.sender;

import io.zensoft.share.model.diesel.RequestResponse;
import io.zensoft.share.model.diesel.RequestStatus;
import io.zensoft.share.service.diesel.RequestSender;
import io.zensoft.share.service.diesel.header.AuthKeyHeader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:diesel.properties")
public class AuthKeyGetRequestSender implements RequestSender {
    @Value("${diesel.authKey.url}")
    private String serverUrlPostRequestReceiver;
    private AuthKeyHeader authKeyHeader;
    private HttpStatus statusCode;
    private String authKey;

    @Autowired
    public AuthKeyGetRequestSender(AuthKeyHeader authKeyHeader) {
        this.authKeyHeader = authKeyHeader;
    }

    public RequestResponse sendGetRequestToGetResponseWithAuthKey(RestTemplate restTemplate) {
        HttpEntity<?> request = new HttpEntity<>(authKeyHeader.getAuthKeyGetterHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
        statusCode = response.getStatusCode();
        authKey = getAuthKeyFromHtmlAsStringResponse(response.getBody());
        RequestResponse requestResponse = new RequestResponse();
        return getFilledResponseFromSender(requestResponse);
    }

    private String getAuthKeyFromHtmlAsStringResponse(String htmlBody) {
        Document document = Jsoup.parse(htmlBody);
        Element authKeyInput = document.select("input[name=auth_key]").first();
        String authKey = authKeyInput.attr("value");
        return authKey;
    }

    @Override
    public RequestResponse getFilledResponseFromSender(RequestResponse requestResponse) {
        if (statusCode.equals(HttpStatus.OK)) {
            requestResponse.setStatus(RequestStatus.SUCCESS);
            return requestResponse;
        }
        requestResponse.setStatus(RequestStatus.FAILED);
        return requestResponse;
    }
    
    public void addHeaderCookie(String sessionId) {
        authKeyHeader.addCookieToHeaders(sessionId);
    }

    public String getAuthKey() {
        return authKey;
    }
}
