package io.zensoft.share.service.Facebook;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.service.PublisherService;
import io.zensoft.share.service.model.VacancyResponseModelService;
import io.zensoft.share.service.model.VacancyModelService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagePostData;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class FacebookPublisherService implements PublisherService {
    private final Properties properties;

    private final String appAccessToken;
    private final String userAccessToken;
    private final String pageAccessToken;

    private final Facebook facebookApp;
    private final Facebook facebookUser;
    private final Facebook facebookPage;

    public FacebookPublisherService(){
        properties = new Properties();

        try {
            properties.load(new FileReader("src/main/resources/facebook.config"));
        } catch (Exception e) {

        }
        appAccessToken = properties.getProperty("appId") + "|" + properties.getProperty("appSecret");
        facebookApp= new FacebookTemplate(appAccessToken, properties.getProperty("appNamespace"), properties.getProperty("appId"));

        userAccessToken = getTestUserAccessToken();
        facebookUser = new FacebookTemplate(userAccessToken, properties.getProperty("appNamespace"), properties.getProperty("appId"));

        pageAccessToken = getPageAccessToken();
        facebookPage = new FacebookTemplate(pageAccessToken, properties.getProperty("appNamespace"), properties.getProperty("appId"));
    }

    public static void main(String[] args) {
        FacebookPublisherService facebookPublisherService = new FacebookPublisherService();
        facebookPublisherService.getUserAccessToken();

    }

    private String getUserAccessToken () {
        String oauthRequestUrl = getOauthRequestUrl();
        Connection.Response response = null;
        Connection connection = Jsoup.connect(oauthRequestUrl).method(Connection.Method.GET).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36").followRedirects(true);
        try {
            response = connection.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
        System.out.println(connection);

        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<String> map = null;
        try {
            map = ((FacebookTemplate) facebookApp).getRestTemplate().exchange( oauthRequestUrl,
                    HttpMethod.GET, (HttpEntity<?>) null, String.class, (Object) uriVariables);
        } catch (Exception e) {

        }

        System.out.println(map.getBody());
        return map.toString();
    }

    private String getOauthRequestUrl() {
        String url = properties.getProperty("oauthUrlTemplate");
        url = url.replace("{appId}", properties.getProperty("appId"));
        url = url.replace("{redirectUrl}", properties.getProperty("redirectUrl"));
        url = url.replace("{responseType}", "token");
        return url;
    }

    @Deprecated // Only for my Test User. Another method for getting access token of ANY user will be written later.
    private String getTestUserAccessToken(){
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map = null;
        try {
            map = ((FacebookTemplate) facebookApp).getRestTemplate().exchange(
                    "https://graph.facebook.com/" + properties.getProperty("appId") + "/accounts/test-users/",
                    HttpMethod.GET, (HttpEntity<?>) null, Map.class, (Object) uriVariables);
        } catch (Exception e) {

        }
        String access_token = ((ArrayList<Map<String, Object>>)map.getBody().get("data")).get(0).get("access_token").toString();
        return access_token;
    }

    private String getPageAccessToken () {
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map = null;
        try {
            map = ((FacebookTemplate) facebookUser).getRestTemplate().exchange(
                    "https://graph.facebook.com/" + properties.getProperty("userId") + "/accounts",
                    HttpMethod.GET, (HttpEntity<?>) null, Map.class, (Object) uriVariables);
        } catch (Exception e) {

        }
        String access_token = ((ArrayList<Map<String, Object>>)map.getBody().get("data")).get(0).get("access_token").toString();
        return access_token;
    }

    @Override
    public VacancyResponse publish(Vacancy vacancy) {
        if (vacancy.getImage() != null) {
            return publishPhoto(vacancy);
        } else {
            return publishText(vacancy);
        }
    }

    private VacancyResponse publishText(Vacancy vacancy) {
        PagePostData pagePostData = new PagePostData(properties.getProperty("pageId"));
        pagePostData = pagePostData.message(vacancy.getTitle());
        pagePostData.link(properties.getProperty("link"), null, null, null, null);
        facebookPage.pageOperations().post(pagePostData);
        return null;
    }


    @Override
    public VacancyResponse getInfo(Vacancy vacancy) {
        return null;
    }

    public VacancyResponse publishPhoto(Vacancy vacancy) {
        String publishPhotoRequestUrl = getPublishPhotoRequestUrl(vacancy);
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map;
        try {
            map = ((FacebookTemplate) facebookPage).getRestTemplate().exchange( publishPhotoRequestUrl,
                    HttpMethod.POST, (HttpEntity<?>) null, Map.class, (Object) uriVariables);
        } catch (Exception e) {

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
        return vacancy.getImage();
    }

    private String getText(Vacancy vacancy) {
        return vacancy.getTitle();
    }

}
