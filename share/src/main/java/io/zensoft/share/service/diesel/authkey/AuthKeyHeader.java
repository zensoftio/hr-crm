package io.zensoft.share.service.diesel.authkey;

import io.zensoft.share.model.diesel.HeaderValue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuthKeyHeader {

    private HttpHeaders authKeyGetterHeaders;
    private HeaderValue headerValue;

    @Autowired
    public AuthKeyHeader(HeaderValue headerValue) {
        authKeyGetterHeaders = new HttpHeaders();
        this.headerValue = headerValue;
        authKeyGetterHeaders.add("Upgrade-Insecure-Requests", headerValue.getUPGRADE_INSECURE_REQUESTS());
        authKeyGetterHeaders.add("User-Agent", headerValue.getUSER_AGENT());
        authKeyGetterHeaders.add("Referer", headerValue.getREFERER_AUTHKEY());
        authKeyGetterHeaders.add("Accept-Language", headerValue.getACCEPT_LANGUAGE());
    }

    public void addCookieToHeaders(String sessionId){
        authKeyGetterHeaders.add("Cookie", "coppa=0; _ga=GA1.2.1282133015.1530688724; _gid=GA1.2.1338586460.1530688724; astratop=1; member_id=0; pass_hash=0; _ga=GA1.2.1282133015.1530688724; _gid=GA1.2.1338586460.1530688724; member_id=0; pass_hash=0; ipsconnect_5ec502fd645bc2f7cc566fb6fdf81f7a=0; _gat=1; ipsconnect_5ec502fd645bc2f7cc566fb6fdf81f7a=1; rteStatus=rte; modtids=%2C; mqtids=%2C; itemMarking_forums_items=eJw9yLERwDAIA8BdXFynwICkkNV83t2Fj3z5a3i5o17E-CbCZCnY053qjhC7lbjNKml2k3-Tyn1cMHohEw8%2C; session_id="+ sessionId);
    }
}
