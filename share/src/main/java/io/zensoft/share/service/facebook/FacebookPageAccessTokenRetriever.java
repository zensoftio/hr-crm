package io.zensoft.share.service.facebook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class FacebookPageAccessTokenRetriever {
    private String userAccessToken;
    private String pageAccessToken;

    public String getZensoftPageAccessToken (){
        log.info("get Zensoft's page access token by pre-assigned user access token");
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map = null;
        try {
            map = new RestTemplate().exchange(
                    "https://graph.facebook.com/me/accounts?access_token=" + userAccessToken,
                    HttpMethod.GET, (HttpEntity<?>) null, Map.class, (Object) uriVariables);
        } catch (Exception e) {
            log.error("error sending request to get page access token", e);
            return null;
        }
        Object object = map.getBody().get("data");
        ArrayList<Map<String, Object>> pageList = (ArrayList<Map<String, Object>>) object;
        pageList.forEach( (page) -> {
            if(page.get("name").toString().contains("Zensoft")){
                setPageAccessToken(page.get("access_token").toString());
            }
        });
        return pageAccessToken;
    }

    public void setUserAccessToken(String userAccessToken) {
        log.info("set user access token");
        this.userAccessToken = userAccessToken;
    }

    private void setPageAccessToken(String pageAccessToken) {
        this.pageAccessToken = pageAccessToken;
    }
}
