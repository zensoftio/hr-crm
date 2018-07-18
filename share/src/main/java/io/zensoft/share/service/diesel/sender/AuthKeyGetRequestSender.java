package io.zensoft.share.service.diesel.sender;

import io.zensoft.share.model.diesel.RequestResponse;
import io.zensoft.share.service.diesel.header.AuthKeyHeader;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:diesel.properties")
@Slf4j
public class AuthKeyGetRequestSender {
    @Value("${diesel.authKey.url}")
    private String serverUrlPostRequestReceiver;
    private AuthKeyHeader authKeyHeader;
    private RequestResponseBuilderService requestResponseBuilderService;
    private String authKey;

    @Autowired
    public AuthKeyGetRequestSender(AuthKeyHeader authKeyHeader, RequestResponseBuilderService requestResponseBuilderService) {
        this.authKeyHeader = authKeyHeader;
        this.requestResponseBuilderService = requestResponseBuilderService;
    }

    public RequestResponse sendGetRequestToGetResponseWithAuthKey(RestTemplate restTemplate) {
        log.info("send GET request to receive html response where we will retrieve 'authKey'");
        HttpEntity<?> request = new HttpEntity<>(authKeyHeader.getAuthKeyGetterHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
        authKey = getAuthKeyFromHtmlAsStringResponse(response.getBody());
        log.info("get authKey from htmlResponse");
        RequestResponse requestResponse = new RequestResponse();
        log.info("return filled RequestResponse");
        return requestResponseBuilderService.getFilledResponseFromGetRequestSenderSender(requestResponse, response.getStatusCode());
    }

    private String getAuthKeyFromHtmlAsStringResponse(String htmlBody) {
        log.info("parse given html page and retrieve 'authKey'");
        Document document = Jsoup.parse(htmlBody);
        Element authKeyInput = document.select("input[name=auth_key]").first();
        String authKey = authKeyInput.attr("value");
        log.info("return retrieved 'authKey'");
        return authKey;
    }

    public void addHeaderCookie(String sessionId) {
        log.info("call method from header class to add header with cookie(sessionId)");
        authKeyHeader.addCookieToHeaders(sessionId);
    }

    public void deleteHeaderCookie() {
        log.info("call method from header class to delete header with cookie(sessionId)");
        authKeyHeader.deleteCookieFromHeaders();
    }

    public String getAuthKey() {
        return authKey;
    }
}
