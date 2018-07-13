package io.zensoft.share.service.facebook;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("facebook.config")
@ConfigurationProperties
public class FacebookConfigs {
    private String userId;
    private String pageId;
    private String appId;
    private String appSecret;
    private String appNamespace;
    private String link;
    private String publishPhotoRequestUrlTemplate;
    private String publishTextRequestUrlTemplate;
}