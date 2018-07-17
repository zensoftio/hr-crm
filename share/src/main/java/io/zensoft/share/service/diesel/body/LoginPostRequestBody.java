package io.zensoft.share.service.diesel.body;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@Component
@PropertySource("classpath:diesel.properties")
@Slf4j
public class LoginPostRequestBody {
    private final String AUTHKEY = "880ea6a14ea49e853634fbdc5015a024";
    private final String REFERER = "http://diesel.elcat.kg/";

    @Value( "${diesel.password}" )
    private String ips_password;
    @Value( "${diesel.username}" )
    private String ips_username;
    
    public LinkedMultiValueMap getCreatedBodyOfRequestInMap() {
        log.info("get created Body of Request placed in LinkedMultiValueMap");
        MultiValueMap<String, String> bodyOfRequest = new LinkedMultiValueMap<>();
        bodyOfRequest.add("auth_key", getAUTHKEY());
        bodyOfRequest.add("ips_password", getIps_password());
        bodyOfRequest.add("ips_username", getIps_username());
        bodyOfRequest.add("referer", getREFERER());

        return (LinkedMultiValueMap) bodyOfRequest;
    }

}
