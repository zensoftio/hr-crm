package io.zensoft.share.service.diesel;

import io.zensoft.share.model.diesel.AuthKeyGetterHeaders;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Data
@Service
public class AuthKeyGetRequestSender {
    private String server;

    @Autowired
    private AuthKeyGetterHeaders authKeyGetterHeaders;

    public AuthKeyGetRequestSender() {
        server = "http://diesel.elcat.kg/index.php?app=forums&module=post&section=post&do=new_post&f=243";
    }

    /**
     * it sends get request to get a response as html
     *
     * @param restTemplate
     * @return
     */

    public String sendGetRequestToGetResponseWithAuthKey(RestTemplate restTemplate)
    {
        HttpEntity<?> request = new HttpEntity<>(authKeyGetterHeaders.getAuthKeyGetterHeaders());

        ResponseEntity<String> response = restTemplate.postForEntity(server, request, String.class);

        return getAuthKeyFromStringResponse(response.getBody());
    }

    /**
     * returns parsed auth_key from html as string
     * yes, it's a hardcode
     *
     * @param htmlBody
     * @return
     */

    private String getAuthKeyFromStringResponse(String htmlBody){
        String auth_key = "auth_key' value='";
        String closeTag = "' />";
        String[] subStr1 = htmlBody.split(auth_key);

        String[] strings = subStr1[1].split(closeTag);

        String resultAuthKey = "";

        for (int i = 0; i < 1; i++) {
            resultAuthKey = resultAuthKey + strings[i];
        }
        return resultAuthKey;
    }

    public void addCookieToHeaders(String sessionId){
        authKeyGetterHeaders.getAuthKeyGetterHeaders().add("Cookie", "coppa=0; _ga=GA1.2.1282133015.1530688724; _gid=GA1.2.1338586460.1530688724; astratop=1; member_id=0; pass_hash=0; _ga=GA1.2.1282133015.1530688724; _gid=GA1.2.1338586460.1530688724; member_id=0; pass_hash=0; ipsconnect_5ec502fd645bc2f7cc566fb6fdf81f7a=0; _gat=1; ipsconnect_5ec502fd645bc2f7cc566fb6fdf81f7a=1; rteStatus=rte; modtids=%2C; mqtids=%2C; itemMarking_forums_items=eJw9yLERwDAIA8BdXFynwICkkNV83t2Fj3z5a3i5o17E-CbCZCnY053qjhC7lbjNKml2k3-Tyn1cMHohEw8%2C; session_id="+ sessionId);
    }
}
