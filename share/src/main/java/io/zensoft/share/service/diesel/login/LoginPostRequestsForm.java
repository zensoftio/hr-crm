package io.zensoft.share.service.diesel.login;

import io.zensoft.share.model.diesel.DieselAccount;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
public class LoginPostRequestsForm {
    /**
     * names of fields are same with required fields in POST Request
     */
    private final String fauth_key = "880ea6a14ea49e853634fbdc5015a024";
    private final String freferer = "http://diesel.elcat.kg/";

    private String ips_password;
    private String ips_username;

    private MultiValueMap<String, String> bodyOfRequest;

    @Autowired
    private DieselAccount dieselAccount;

    public LoginPostRequestsForm() {
        bodyOfRequest = new LinkedMultiValueMap<>();
        ips_password = dieselAccount.getPassword();
        ips_username = dieselAccount.getUsername();
    }

    public LinkedMultiValueMap getCreatedBodyOfRequestInMap() {
        bodyOfRequest.add("auth_key", getFauth_key());
        bodyOfRequest.add("ips_password", getIps_password());
        bodyOfRequest.add("ips_username", getIps_username());
        bodyOfRequest.add("referer", getFreferer());

        return (LinkedMultiValueMap) bodyOfRequest;
    }

}
