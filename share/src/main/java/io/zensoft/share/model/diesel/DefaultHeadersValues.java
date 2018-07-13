package io.zensoft.share.model.diesel;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DefaultHeadersValues {
    private final String UPGRADE_INSECURE_REQUESTS = "1";
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36";
    private final String ACCEPT_LANGUAGE = "ru,en;q=0.9";
    private final String ORIGIN = "http://diesel.elcat.kg";
    private final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private final String X_DEVTOOLS_EMULATE_NETWORK_CONDITIONS_CLIENT_ID = "696F5689A6AA8D0342F16C6EF65199F0";
    private final String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
    private final String REFERER_AUTHKEY = "http://diesel.elcat.kg/index.php?showforum=243";
    private final String REFERER_LOGIN = "http://diesel.elcat.kg/";
    private final String REFERER_PUBLICATION = "http://diesel.elcat.kg/index.php?app=forums&module=post&section=post&do=new_post&f=243";
    private final String ACCEPT_ENCODING = "gzip, deflate, br";
}
