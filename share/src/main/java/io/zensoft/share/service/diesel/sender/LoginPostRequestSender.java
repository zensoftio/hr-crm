package io.zensoft.share.service.diesel.sender;

import io.zensoft.share.model.diesel.RequestResponse;
import io.zensoft.share.model.diesel.RequestStatus;
import io.zensoft.share.service.diesel.RequestSender;
import io.zensoft.share.service.diesel.body.LoginPostRequestBody;
import io.zensoft.share.service.diesel.header.LoginHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@PropertySource("classpath:diesel.properties")
public class LoginPostRequestSender implements RequestSender {
    @Value("${diesel.login.url}")
    private String serverUrlPostRequestReceiver;
    private LoginHeader loginHeader;
    private LoginPostRequestBody loginPostRequestBody;
    private HttpStatus statusCode;
    private String sessionId;

    @Autowired
    public LoginPostRequestSender(LoginPostRequestBody loginPostRequestBody, LoginHeader loginHeader) {
        this.loginPostRequestBody = loginPostRequestBody;
        this.loginHeader = loginHeader;
    }

    public RequestResponse sendPostRequestForLogin(RestTemplate restTemplate) {
        HttpEntity<?> request = new HttpEntity<>(loginPostRequestBody.getCreatedBodyOfRequestInMap(), loginHeader.getLoginHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
        response.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
        List<String> setCookie = Collections.singletonList(response.getHeaders().getFirst("Set-Cookie"));
        String sessionIdContainer = setCookie.get(0);
        sessionId = getSessionIdFromResponse(sessionIdContainer);
        statusCode = response.getStatusCode();
        RequestResponse requestResponse = new RequestResponse();

        return getFilledResponseFromSender(requestResponse);
    }

    private String getSessionIdFromResponse(String headers) {
        String delimiter = ";";
        String equalsSymbol = "=";
        String[] partOfHeaders = headers.split(equalsSymbol);

        String[] sessionIdContainer = partOfHeaders[1].split(delimiter);
        String resultSessionId = "";
        resultSessionId = resultSessionId + sessionIdContainer[0];

        return resultSessionId;
    }

    @Override
    public RequestResponse getFilledResponseFromSender(RequestResponse requestResponse) {
        if (statusCode.equals(HttpStatus.FOUND)) {
            requestResponse.setStatus(RequestStatus.SUCCESS);
            return requestResponse;
        }
        requestResponse.setStatus(RequestStatus.FAILED);
        return requestResponse;
    }

    public String getSessionId() {
        return sessionId;
    }
}
