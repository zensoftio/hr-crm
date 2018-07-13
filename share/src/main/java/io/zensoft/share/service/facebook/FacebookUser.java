package io.zensoft.share.service.facebook;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FacebookUser {
    private String accessToken;
    private String pageAccessToken;

    public String getZensoftPageAccessToken (){
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map = null;
        try {
            map = new RestTemplate().exchange(
                    "https://graph.facebook.com/me/accounts?access_token=" + accessToken,
                    HttpMethod.GET, (HttpEntity<?>) null, Map.class, (Object) uriVariables);
        } catch (Exception e) {
            e.printStackTrace();
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

    public void setPageAccessToken(String pageAccessToken) {
        this.pageAccessToken = pageAccessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
