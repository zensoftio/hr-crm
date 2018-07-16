package io.zensoft.share.service.facebook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class FacebookRequestSender {

    protected ResponseEntity<Map> post(String url){
        log.info("send post request to Facebook Graph Api");
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map = new RestTemplate().exchange( url,
                HttpMethod.POST, (HttpEntity<?>) null, Map.class, (Object) uriVariables);
        return map;
    }
}
