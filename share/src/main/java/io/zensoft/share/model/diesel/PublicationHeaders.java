package io.zensoft.share.model.diesel;

import lombok.Data;
import org.springframework.http.HttpHeaders;

@Data
public class PublicationHeaders {

    /**
     * "f" stands for "final"
     * and it help us to read UpperCase header names
     * "fContent_Type" looks better than "content-Type"
     * all headers what we send in "HttpHeaders" should begin with UpperCase letter,
     * that's why adding "f" is good solution
     */
    private HttpHeaders publicationHeaders;
    private final String fOrigin = "http://diesel.elcat.kg";
    private final String fUpgrade_Insecure_Requests = "1";
    private final String fContent_Type = "application/x-www-form-urlencoded";
    private final String fUser_Agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36";
    private final String fX_DevTools_Emulate_Network_Conditions_Client_Id = "696F5689A6AA8D0342F16C6EF65199F0";
    private final String fAccept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
    private final String fReferer = "http://diesel.elcat.kg/index.php?app=forums&module=post&section=post&do=new_post&f=243";
    private final String fAccept_Encoding = "gzip, deflate";
    private final String fAccept_Language = "ru,en;q=0.9";

    public PublicationHeaders(){
        this.publicationHeaders = new HttpHeaders();

        publicationHeaders.add("Origin", getFOrigin());
        publicationHeaders.add("Upgrade-Insecure-Requests", getFUpgrade_Insecure_Requests());
        publicationHeaders.add("Content-Type", getFContent_Type());
        publicationHeaders.add("DieselAccount-Agent", getFUser_Agent());
        publicationHeaders.add("Accept", getFAccept());
        publicationHeaders.add("Referer", getFReferer());
        publicationHeaders.add("Accept-Encoding", getFAccept_Encoding());
        publicationHeaders.add("Accept-Language", getFAccept_Language());
    }
}
