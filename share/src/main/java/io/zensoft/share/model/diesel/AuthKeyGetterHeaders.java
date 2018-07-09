package io.zensoft.share.model.diesel;

import lombok.Data;
import org.springframework.http.HttpHeaders;

@Data
public class AuthKeyGetterHeaders {

    /**
     * "f" stands for "final"
     * and it help us to read UpperCase header names
     * "fUpgrade_Insecure_Requests" looks better than "Upgrade_Insecure_Requests"
     * all headers what we send in "HttpHeaders" should begin with UpperCase letter,
     * that's why adding "f" is good solution
     */
    private HttpHeaders authKeyGetterHeaders;

    private final String fUpgrade_Insecure_Requests = "1";
    private final String fUser_Agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36";
    private final String fReferer = "http://diesel.elcat.kg/index.php?showforum=243";
    //private final String fAccept_Encoding = "gzip, deflate";
    private final String fAccept_Language = "ru,en;q=0.9";

    public AuthKeyGetterHeaders() {
        this.authKeyGetterHeaders = new HttpHeaders();

        authKeyGetterHeaders.add("Upgrade-Insecure-Requests", getFUpgrade_Insecure_Requests());
        authKeyGetterHeaders.add("User-Agent", getFUser_Agent());
        authKeyGetterHeaders.add("Referer", getFReferer());
        //authKeyGetterHeaders.add("Accept-Encoding", getFAccept_Encoding());
        authKeyGetterHeaders.add("Accept-Language", getFAccept_Language());

    }
}
