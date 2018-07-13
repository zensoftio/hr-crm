package io.zensoft.share.service.diesel.body;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@Component
public class LoginPostRequestBody {
    private final String fauth_key = "880ea6a14ea49e853634fbdc5015a024";
    private final String freferer = "http://diesel.elcat.kg/";

    @Value( "${diesel.password}" )
    private String ips_password;
    @Value( "${diesel.username}" )
    private String ips_username;
    
    public LinkedMultiValueMap getCreatedBodyOfRequestInMap() {
        MultiValueMap<String, String> bodyOfRequest = new LinkedMultiValueMap<>();
        bodyOfRequest.add("auth_key", getFauth_key());
        bodyOfRequest.add("ips_password", getIps_password());
        bodyOfRequest.add("ips_username", getIps_username());
        bodyOfRequest.add("referer", getFreferer());

        return (LinkedMultiValueMap) bodyOfRequest;
    }

}
