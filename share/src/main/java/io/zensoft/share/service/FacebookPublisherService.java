package io.zensoft.share.service;

import io.zensoft.share.model.Post;
import io.zensoft.share.model.PostResponse;
import io.zensoft.share.service.model.PostResponseService;
import io.zensoft.share.service.model.PostService;
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

public class FacebookPublisherService extends BasePublisherService {
    private final Properties properties;

    private final String appAccessToken;
    private final String userAccessToken;
    private final String pageAccessToken;

    private final Facebook facebookApp;
    private final Facebook facebookUser;
    private final Facebook facebookPage;

    public FacebookPublisherService(RestTemplate restTemplate, PostService postService, PostResponseService postResponseService) throws Exception {
        super(restTemplate, postService, postResponseService);
        properties = new Properties();
        properties.load(new FileReader("src/main/resources/facebookConfig"));

        appAccessToken = properties.getProperty("appId") + "|" + properties.getProperty("appSecret");
        facebookApp= new FacebookTemplate(appAccessToken, properties.getProperty("appNamespace"), properties.getProperty("appId"));

        userAccessToken = getTestUserAccessToken();
        facebookUser = new FacebookTemplate(userAccessToken, properties.getProperty("appNamespace"), properties.getProperty("appId"));

        pageAccessToken = getPageAccessToken();
        facebookPage = new FacebookTemplate(pageAccessToken, properties.getProperty("appNamespace"), properties.getProperty("appId"));
    }


    public static void main(String[] args) throws Exception {
        FacebookPublisherService facebookPublisherService =
                new FacebookPublisherService(null, null, null);
        facebookPublisherService.publishPhoto((Post)null);
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

    public PostResponse publish(Post post) {
        PagePostData pagePostData = new PagePostData(properties.getProperty("pageId"));
        pagePostData.message(getText(post));
        String postId = facebookUser.pageOperations().post(pagePostData);
        return null;
    }

    public PostResponse publishPhoto(Post post) throws Exception {
        String publishPhotoRequestUrl = getPublishPhotoRequestUrl(post);
        Map<String, String> uriVariables = new LinkedHashMap<>();
        ResponseEntity<Map> map;
        try {
            map = ((FacebookTemplate) facebookPage).getRestTemplate().exchange( publishPhotoRequestUrl,
                    HttpMethod.POST, (HttpEntity<?>) null, Map.class, (Object) uriVariables);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        System.out.println(map);
        return null;
    }

    private String getPublishPhotoRequestUrl(Post post) {
        String url = properties.getProperty("publishPhotoRequestUrlTemplate");
        url = url.replace("{pageId}", properties.getProperty("pageId"));
        url = url.replace("{photoUrl}", getPhotoUrl(post));
        url = url.replace("{caption}", getText(post));
        url = url.replace("{isPublished}", "true");
        return url;
    }

    private String getPhotoUrl(Post post) {
        return "http://lmndeit.kg/wp-content/uploads/2016/09/1066_178169975899042_3386044648562112580_n.png";
    }

    private String getText(Post post) {
        return "Default text";
    }

}
