package io.zensoft.share.service.diesel.login;

import io.zensoft.share.model.diesel.LoginHeaders;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Data
@Service
public class LoginPostRequestSender {
    private String server;

    @Autowired
    private LoginHeaders loginHeaders;
    @Autowired
    private LoginPostRequestsForm loginPostRequestsForm;

    public LoginPostRequestSender() {
        server = "https://diesel.elcat.kg/index.php?app=core&module=global&section=login&do=process";
    }

    /**
     * Sends POST requests to "server" with headers and body
     * gets response with "Set-Cookie" section
     *
     * returns sessionId, got from response with method getSessionIdFromResponse()
     *
     * @param restTemplate
     * @return
     */
    public String sendPostRequestForLogin(RestTemplate restTemplate) {

        HttpEntity<?> request = new HttpEntity<>(loginPostRequestsForm.getCreatedBodyOfRequestInMap(), loginHeaders.getLoginHeaders());

        ResponseEntity<String> response = restTemplate.postForEntity(server, request, String.class);
        response.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);

        List<String> setCookie = Collections.singletonList(response.getHeaders().getFirst("Set-Cookie"));
        String sessionId = setCookie.get(0);

        return getSessionIdFromResponse(sessionId);
    }

    /**
     * returns parsed sessionId
     * yes, it's a hardcode
     *
     * @param headers
     * @return
     */
    private String getSessionIdFromResponse(String headers) {
        String delimeter = ";";
        String equalsSymbol = "=";
        String[] subString = headers.split(equalsSymbol);

        String[] strings = subString[1].split(delimeter);

        String resultSessionId = "";

        for (int i = 0; i < 1; i++) {
            resultSessionId = resultSessionId + strings[i];
        }

        return resultSessionId;
    }

}
