package io.zensoft.share.service.diesel.login;

import io.zensoft.share.model.diesel.RequestStatus;
import io.zensoft.share.model.diesel.RequestsResponse;
import io.zensoft.share.service.diesel.RequestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;

@Service
public class LoginPostRequestSender implements RequestSender {
    @Value("${diesel.login.url}")
    private String serverUrlPostRequestReceiver;
    private LoginHeaders loginHeaders;
    private LoginPostRequestsForm loginPostRequestsForm;
    private String statusCode;
    private String sessionId;

    @Autowired
    public LoginPostRequestSender(LoginPostRequestsForm loginPostRequestsForm,
                                  LoginHeaders loginHeaders){
        this.loginPostRequestsForm = loginPostRequestsForm;
        this.loginHeaders = loginHeaders;
    }

    public RequestsResponse sendPostRequestForLogin(RestTemplate restTemplate) {
        HttpEntity<?> request = new HttpEntity<>(loginPostRequestsForm.getCreatedBodyOfRequestInMap(), loginHeaders.getLoginHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
        response.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
        List<String> setCookie = Collections.singletonList(response.getHeaders().getFirst("Set-Cookie"));
        String sessionIdContainer = setCookie.get(0);
        sessionId = getSessionIdFromResponse(sessionIdContainer);
        RequestsResponse requestsResponse = new RequestsResponse();
        return getFilledResponseFromSender(requestsResponse);
    }

    private String getSessionIdFromResponse(String headers) {
        String delimiter = ";";
        String equalsSymbol = "=";
        String[] partOfHeaders = headers.split(equalsSymbol);

        String[] sessionIdContainer = partOfHeaders[1].split(delimiter);
        String resultSessionId = "";
        for (int i = 0; i < 1; i++) {
            resultSessionId = resultSessionId + sessionIdContainer[i];
        }
        return resultSessionId;
    }

    @Override
    public RequestsResponse getFilledResponseFromSender(RequestsResponse requestsResponse) {
        if(!statusCode.equals("302")){
            requestsResponse.setStatus(RequestStatus.FAILED);
        }
        if(statusCode.equals("302")){
            requestsResponse.setStatus(RequestStatus.SUCCESS);
        }
        return requestsResponse;
    }

    public String getSessionId() {
        return sessionId;
    }
}
