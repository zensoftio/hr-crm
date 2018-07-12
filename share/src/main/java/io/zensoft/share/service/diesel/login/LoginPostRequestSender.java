package io.zensoft.share.service.diesel.login;

import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.model.VacancyStatus;
import io.zensoft.share.service.diesel.RequestSender;
import lombok.Data;
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

    @Autowired
    public LoginPostRequestSender(LoginPostRequestsForm loginPostRequestsForm,
                                  LoginHeaders loginHeaders){
        this.loginPostRequestsForm = loginPostRequestsForm;
        this.loginHeaders = loginHeaders;
    }

    public String sendPostRequestForLogin(RestTemplate restTemplate) {
        HttpEntity<?> request = new HttpEntity<>(loginPostRequestsForm.getCreatedBodyOfRequestInMap(), loginHeaders.getLoginHeaders());

        ResponseEntity<String> response = restTemplate.postForEntity(serverUrlPostRequestReceiver, request, String.class);
        response.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);

        List<String> setCookie = Collections.singletonList(response.getHeaders().getFirst("Set-Cookie"));
        String sessionId = setCookie.get(0);

        return getSessionIdFromResponse(sessionId);
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
    public VacancyResponse getFilledResponseFromSender(VacancyResponse vacancyResponse) {
        if(!statusCode.equals("302")){
            vacancyResponse.setMessage("Post request for login is failed, Status code: " + statusCode+".");
            vacancyResponse.setStatus(VacancyStatus.FAILED);
        }
        if(statusCode.equals("302")){
            vacancyResponse.setMessage("Post request for login sent successfully, Status code: " + statusCode+".");
            vacancyResponse.setStatus(VacancyStatus.SUCCESS);
        }
        return vacancyResponse;
    }
}
