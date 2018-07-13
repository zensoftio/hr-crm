package io.zensoft.share.service.facebook;

import io.zensoft.share.model.VacancyStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

public class FacebookRequestSender {

    public static ResponseEntity<Map> postRequest(String url){
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map = new RestTemplate().exchange( url,
                HttpMethod.POST, (HttpEntity<?>) null, Map.class, (Object) uriVariables);
        return map;
    }
}
