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
    private FacebookRequestSender facebookRequestSender;
    private FacebookUrlBuilder facebookUrlBuilder;

    @Autowired
    public FacebookPublisherService(FacebookConfigs facebookConfigs,
                                    FacebookPageAccessTokenRetriever facebookPageAccessTokenRetriever,
                                    FacebookRequestSender facebookRequestSender,
                                    FacebookUrlBuilder facebookUrlBuilder){
        this.facebookConfigs = facebookConfigs;
        this.facebookPageAccessTokenRetriever = facebookPageAccessTokenRetriever;
        this.facebookRequestSender = facebookRequestSender;
        this.facebookUrlBuilder = facebookUrlBuilder;
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
        String url = facebookUrlBuilder.getPublishTextRequestUrl(vacancy, pageAccessToken);
        VacancyResponse vacancyResponse = post(url);
        vacancyResponse.setVacancy(vacancy);
        return vacancyResponse;
    }

    private VacancyResponse publishPhoto(Vacancy vacancy) {
        String url = facebookUrlBuilder.getPublishPhotoRequestUrl(vacancy, pageAccessToken);
        VacancyResponse vacancyResponse = post(url);
        vacancyResponse.setVacancy(vacancy);
        return vacancyResponse;
    }

    private VacancyResponse post(String url) {
        VacancyResponse vacancyResponse = new VacancyResponse();
        vacancyResponse.setPublisherServiceType(PublisherServiceType.FACEBOOK);
        try {
            ResponseEntity<Map> map = facebookRequestSender.post(url);
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

}
