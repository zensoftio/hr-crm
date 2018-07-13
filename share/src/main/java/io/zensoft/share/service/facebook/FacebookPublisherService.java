package io.zensoft.share.service.facebook;

import io.zensoft.share.model.PublisherServiceType;
import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.VacancyResponse;
import io.zensoft.share.model.VacancyStatus;
import io.zensoft.share.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.net.URL;
import java.util.Date;
import java.util.Map;

@Service
public class FacebookPublisherService implements PublisherService {

    private String userAccessToken;
    private String pageAccessToken;

    private Facebook facebookPage;
    private FacebookConfigs facebookConfigs;
    private FacebookPageAccessTokenRetriever facebookPageAccessTokenRetriever;

    @Autowired
    public FacebookPublisherService(FacebookConfigs facebookConfigs, FacebookPageAccessTokenRetriever facebookPageAccessTokenRetriever){
        this.facebookConfigs = facebookConfigs;
        this.facebookPageAccessTokenRetriever = facebookPageAccessTokenRetriever;
    }

    @Override
    public VacancyResponse publish(Vacancy vacancy) {
        init(vacancy);
        if (isValidImageUrl(vacancy.getImage())) {
            return publishPhoto(vacancy);
        }
        return publishText(vacancy);
    }

    private void init(Vacancy vacancy) {
        userAccessToken = vacancy.getFacebookUserAccessToken();
        facebookPageAccessTokenRetriever.setUserAccessToken(userAccessToken);
        pageAccessToken = facebookPageAccessTokenRetriever.getZensoftPageAccessToken();
        facebookPage = new FacebookTemplate(pageAccessToken, facebookConfigs.getAppNamespace(), facebookConfigs.getAppId());
    }

    private boolean isValidImageUrl(String imageUrl) {
        Image image;
        try {
            image = ImageIO.read(new URL(imageUrl));
        } catch (Exception e) {
            System.out.println(imageUrl);
            return false;
        }
        return image != null;
    }

    private VacancyResponse publishText(Vacancy vacancy) {
        String url = getPublishTextRequestUrl(vacancy);
        VacancyResponse vacancyResponse = post(url);
        vacancyResponse.setVacancy(vacancy);
        return vacancyResponse;
    }

    private VacancyResponse publishPhoto(Vacancy vacancy) {
        String url = getPublishPhotoRequestUrl(vacancy);
        VacancyResponse vacancyResponse = post(url);
        vacancyResponse.setVacancy(vacancy);
        return vacancyResponse;
    }

    private VacancyResponse post(String url) {
        VacancyResponse vacancyResponse = new VacancyResponse();
        vacancyResponse.setPublisherServiceType(PublisherServiceType.FACEBOOK);
        try {
            ResponseEntity<Map> map = FacebookRequestSender.postRequest(url);
            vacancyResponse.setStatus(VacancyStatus.SUCCESS);
            vacancyResponse.setUrl("https://www.facebook.com/" + map.getBody().get("id"));
            vacancyResponse.setPublishDate(new Date());
            vacancyResponse.setMessage(map.toString());
            return vacancyResponse;
        } catch (Exception e) {
            vacancyResponse.setStatus(VacancyStatus.FAILED);
            vacancyResponse.setMessage(e.getMessage());
            return vacancyResponse;
        }
    }

    private String getPublishTextRequestUrl(Vacancy vacancy) {
        String url = facebookConfigs.getPublishTextRequestUrlTemplate();
        url = url.replace("{pageId}", facebookConfigs.getPageId());
        url = url.replace("{message}", vacancy.getTitle());
        url = url.replace("{access_token}", pageAccessToken);
        return url;
    }

    private String getPublishPhotoRequestUrl(Vacancy vacancy) {
        String url = facebookConfigs.getPublishPhotoRequestUrlTemplate();
        url = url.replace("{pageId}", facebookConfigs.getPageId());
        url = url.replace("{photoUrl}", getPhotoUrl(vacancy));
        url = url.replace("{caption}", getText(vacancy));
        url = url.replace("{isPublished}", "true");
        url = url.replace("{access_token}", pageAccessToken);
        return url;
    }

    private String getPhotoUrl(Vacancy vacancy) {
        return vacancy.getImage();
    }

    private String getText(Vacancy vacancy) {
        return vacancy.getTitle();
    }
}
