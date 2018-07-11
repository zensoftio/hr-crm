package io.zensoft.share.service.diesel.publication;

import io.zensoft.share.model.diesel.DefaultHeadersValues;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.xml.ws.Action;

@Data
@Component
public class PublicationHeaders {

    private HttpHeaders publicationHeaders;
    private DefaultHeadersValues defaultHeadersValues;

    @Autowired
    public PublicationHeaders(DefaultHeadersValues defaultHeadersValues){
        publicationHeaders = new HttpHeaders();
        this.defaultHeadersValues = defaultHeadersValues;
        publicationHeaders.add("Origin", defaultHeadersValues.getORIGIN());
        publicationHeaders.add("Upgrade-Insecure-Requests", defaultHeadersValues.getUPGRADE_INSECURE_REQUESTS());
        publicationHeaders.add("Content-Type", defaultHeadersValues.getCONTENT_TYPE());
        publicationHeaders.add("User-Agent", defaultHeadersValues.getUSER_AGENT());
        publicationHeaders.add("Accept", defaultHeadersValues.getACCEPT());
        publicationHeaders.add("Referer", defaultHeadersValues.getREFERER_PUBLICATION());
        publicationHeaders.add("Accept-Language", defaultHeadersValues.getACCEPT_LANGUAGE());
    }

    public void addCookieToHeaders(String sessionId){
        publicationHeaders.add("Cookie", "coppa=0; _ga=GA1.2.1282133015.1530688724; _gid=GA1.2.1338586460.1530688724; astratop=1; member_id=0; pass_hash=0; _ga=GA1.2.1282133015.1530688724; _gid=GA1.2.1338586460.1530688724; member_id=0; pass_hash=0; ipsconnect_5ec502fd645bc2f7cc566fb6fdf81f7a=0; _gat=1; ipsconnect_5ec502fd645bc2f7cc566fb6fdf81f7a=1; rteStatus=rte; modtids=%2C; mqtids=%2C; itemMarking_forums_items=eJw9yLERwDAIA8BdXFynwICkkNV83t2Fj3z5a3i5o17E-CbCZCnY053qjhC7lbjNKml2k3-Tyn1cMHohEw8%2C; session_id="+ sessionId);
    }
}
