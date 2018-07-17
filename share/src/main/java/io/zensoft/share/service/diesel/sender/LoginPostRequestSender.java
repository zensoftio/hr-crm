package io.zensoft.share.service.diesel.sender;

import io.zensoft.share.model.diesel.RequestResponse;
import io.zensoft.share.service.diesel.body.LoginPostRequestBody;
import io.zensoft.share.service.diesel.header.LoginHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@PropertySource("classpath:diesel.properties")
@Slf4j
public class LoginPostRequestSender {
    @Value("${diesel.login.url}")
    private String serverUrlPostRequestReceiver;
    private LoginHeader loginHeader;
    private LoginPostRequestBody loginPostRequestBody;
    private RequestResponseBuilderService requestResponseBuilderService;
    private String sessionId;

    @Autowired
    public LoginPostRequestSender(LoginPostRequestBody loginPostRequestBody,
                                  LoginHeader loginHeader,
                                  RequestResponseBuilderService requestResponseBuilderService) {
        this.loginPostRequestBody = loginPostRequestBody;
        this.loginHeader = loginHeader;
        this.requestResponseBuilderService = requestResponseBuilderService;
    }

    public RequestResponse sendPostRequestForLogin(RestTemplate restTemplate) {
        log.info("send POST request to login and receive responseHeaders where we will retrieve 'sessionId'");
        HttpEntity<?> request = new HttpEntity<>(loginPostRequestBody.getCreatedBodyOfRequestInMap(), loginHeader.getLoginHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
        List<String> setCookie = Collections.singletonList(response.getHeaders().getFirst("Set-Cookie"));
        String sessionIdContainer = setCookie.get(0);
        log.info("get sessionId from ResponseHeaders");
        sessionId = getSessionIdFromResponse(sessionIdContainer);
        RequestResponse requestResponse = new RequestResponse();
        log.info("return filled RequestResponse");
        return requestResponseBuilderService.getFilledResponseFromPostRequestSenderSender(requestResponse, response.getStatusCode());
    }

    private String getSessionIdFromResponse(String headers) {
        log.info("get sessionId from responseHeaders");
        String delimiter = ";";
        String equalsSymbol = "=";
        String[] partOfHeaders = headers.split(equalsSymbol);

        String[] sessionIdContainer = partOfHeaders[1].split(delimiter);
        String resultSessionId = "";
        resultSessionId = resultSessionId + sessionIdContainer[0];
        log.info("return retrieved sessionId");
        return resultSessionId;
    }

    public String getSessionId() {
        return sessionId;
    }
}
