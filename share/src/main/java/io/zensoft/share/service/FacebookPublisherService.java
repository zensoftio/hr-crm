package io.zensoft.share.service;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.service.model.VacancyResponseModelService;
import io.zensoft.share.service.model.VacancyModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagePostData;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class FacebookPublisherService {
    private final Properties properties;

    private final String appAccessToken;
    private final String userAccessToken;
    private final String pageAccessToken;

    private final Facebook facebookApp;
    private final Facebook facebookUser;
    private final Facebook facebookPage;

    public FacebookPublisherService() throws Exception {
        properties = new Properties();
        properties.load(new FileReader("src/main/resources/facebookConfig"));

        appAccessToken = properties.getProperty("appId") + "|" + properties.getProperty("appSecret");
        facebookApp= new FacebookTemplate(appAccessToken, properties.getProperty("appNamespace"), properties.getProperty("appId"));

        userAccessToken = getTestUserAccessToken();
        facebookUser = new FacebookTemplate(userAccessToken, properties.getProperty("appNamespace"), properties.getProperty("appId"));

        pageAccessToken = getPageAccessToken();
        facebookPage = new FacebookTemplate(pageAccessToken, properties.getProperty("appNamespace"), properties.getProperty("appId"));
    }

    @Deprecated // Only for my Test User. Another method for getting access token of ANY user will be written later.
    private String getTestUserAccessToken() throws Exception {
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map;
        try {
            map = ((FacebookTemplate) facebookApp).getRestTemplate().exchange(
                    "https://graph.facebook.com/" + properties.getProperty("appId") + "/accounts/test-users/",
                    HttpMethod.GET, (HttpEntity<?>) null, Map.class, (Object) uriVariables);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        String access_token = ((ArrayList<Map<String, Object>>)map.getBody().get("data")).get(0).get("access_token").toString();
        return access_token;
    }

    private String getPageAccessToken () throws Exception {
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map;
        try {
            map = ((FacebookTemplate) facebookUser).getRestTemplate().exchange(
                    "https://graph.facebook.com/" + properties.getProperty("userId") + "/accounts",
                    HttpMethod.GET, (HttpEntity<?>) null, Map.class, (Object) uriVariables);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        String access_token = ((ArrayList<Map<String, Object>>)map.getBody().get("data")).get(0).get("access_token").toString();
        return access_token;
    }

    public VacancyResponse publish(Vacancy vacancy) {
        PagePostData pagePostData = new PagePostData(properties.getProperty("pageId"));
        pagePostData.message(getText(vacancy));
        String postId = facebookUser.pageOperations().post(pagePostData);
        return null;
    }

    public VacancyResponse publishPhoto(Vacancy vacancy) throws Exception {
        String publishPhotoRequestUrl = getPublishPhotoRequestUrl(vacancy);
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map;
        try {
            map = ((FacebookTemplate) facebookPage).getRestTemplate().exchange( publishPhotoRequestUrl,
                    HttpMethod.POST, (HttpEntity<?>) null, Map.class, (Object) uriVariables);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    private String getPublishPhotoRequestUrl(Vacancy vacancy) {
        String url = properties.getProperty("publishPhotoRequestUrlTemplate");
        url = url.replace("{pageId}", properties.getProperty("pageId"));
        url = url.replace("{photoUrl}", getPhotoUrl(vacancy));
        url = url.replace("{caption}", getText(vacancy));
        url = url.replace("{isPublished}", "true");
        return url;
    }

    private String getPhotoUrl(Vacancy vacancy) {
        return "http://lmndeit.kg/wp-content/uploads/2016/09/1066_178169975899042_3386044648562112580_n.png";
    }

    private String getText(Vacancy vacancy) {
        return "Default text";
    }

}
