package io.zensoft.share.config.jobkg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by temirlan on 7/12/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@PropertySource("classpath:jobkg.properties")
@ConfigurationProperties
public class JobKgUserProperties {
    @Value("${jobkg.username}")
    private String username;
    @Value("${jobkg.password}")
    private String password;
}
