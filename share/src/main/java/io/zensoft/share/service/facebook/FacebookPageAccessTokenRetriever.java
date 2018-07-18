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
    private String pageId;

    public String getZensoftPageAccessToken (){
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map = null;
        try {
            log.info("sending request to get pages to Facebook Graph Api by user access token", userAccessToken);
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
                setPageId(page.get("id").toString());
            }
        });
        log.info("page access token is retrieved successfully", pageAccessToken);
        return pageAccessToken;
    }

    public void setUserAccessToken(String userAccessToken) {
        log.info("setting user access token to FacebookPageAccessTokenRetriever", userAccessToken);
        this.userAccessToken = userAccessToken;
    }

    private void setPageAccessToken(String pageAccessToken) {
        this.pageAccessToken = pageAccessToken;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
