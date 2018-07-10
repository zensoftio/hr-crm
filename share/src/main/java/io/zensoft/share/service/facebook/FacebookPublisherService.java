package io.zensoft.share.service.facebook;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.service.PublisherService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagePostData;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class FacebookPublisherService implements PublisherService {
    private final Properties properties;

    private final String appAccessToken;
    private String userAccessToken;
    private String pageAccessToken;

    private final Facebook facebookApp;
    private Facebook facebookUser;
    private Facebook facebookPage;

    public FacebookPublisherService(){
        properties = new Properties();

        try {
            properties.load(new FileReader("src/main/resources/facebook.config"));
        } catch (Exception e) {

        }
        appAccessToken = properties.getProperty("appId") + "|" + properties.getProperty("appSecret");
        facebookApp= new FacebookTemplate(appAccessToken, properties.getProperty("appNamespace"), properties.getProperty("appId"));
    }

    private void setPageAccessToken(String pageAccessToken) {
        this.pageAccessToken = pageAccessToken;
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
        Object testUsers = map.getBody().get("data");
        ArrayList<Map<String, Object>> testUsersList = (ArrayList<Map<String, Object>>)testUsers;
        String access_token = testUsersList.get(0).get("access_token").toString();
        return access_token;
    }

    private void setPageAccessToken () {
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map = null;
        try {
            map = ((FacebookTemplate) facebookUser).getRestTemplate().exchange(
                    "https://graph.facebook.com/" + properties.getProperty("userId") + "/accounts",
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
    }

    @Override
    public VacancyResponse publish(Vacancy vacancy) {
        init(vacancy);
        if (isValidImageUrl(vacancy.getImage())) {
            return publishPhoto(vacancy);
        }
        return publishText(vacancy);
    }

    private boolean isValidImageUrl(String imageUrl) {
        Image image;
        try {
            image = ImageIO.read(new URL(imageUrl));
        } catch (Exception e) {
            return false;
        }
        return image != null;
    }

    private void init(Vacancy vacancy) {
        userAccessToken = vacancy.getFacebookUserAccessToken();
        facebookUser = new FacebookTemplate(userAccessToken, properties.getProperty("appNamespace"), properties.getProperty("appId"));

        setPageAccessToken();
        facebookPage = new FacebookTemplate(pageAccessToken, properties.getProperty("appNamespace"), properties.getProperty("appId"));
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
            e.printStackTrace();
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
