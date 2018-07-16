package io.zensoft.share.service.facebook;

import io.zensoft.share.model.Vacancy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FacebookUrlBuilder {
    private FacebookConfigs facebookConfigs;

    @Autowired
    public FacebookUrlBuilder(FacebookConfigs facebookConfigs) {
        this.facebookConfigs = facebookConfigs;
    }

    public String getPublishPhotoRequestUrl(Vacancy vacancy, String pageAccessToken) {
        log.info("build url for publish photo request to Facebook Graph Api");
        String url = facebookConfigs.getPublishPhotoRequestUrlTemplate();
        url = url.replace("{pageId}", facebookConfigs.getPageId());
        url = url.replace("{photoUrl}", vacancy.getImage());
        url = url.replace("{caption}", getText(vacancy));
        url = url.replace("{isPublished}", "true");
        url = url.replace("{access_token}", pageAccessToken);
        return url;
    }

    public String getPublishTextRequestUrl(Vacancy vacancy, String pageAccessToken) {
        log.info("build url for post request to Facebook Graph Api");
        String url = facebookConfigs.getPublishTextRequestUrlTemplate();
        url = url.replace("{pageId}", facebookConfigs.getPageId());
        url = url.replace("{message}", vacancy.getTitle());
        url = url.replace("{access_token}", pageAccessToken);
        return url;
    }


    private String getText(Vacancy vacancy) {
        return vacancy.getTitle();
    }
}
