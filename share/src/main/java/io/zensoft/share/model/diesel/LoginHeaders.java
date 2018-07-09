package io.zensoft.share.model.diesel;

import lombok.Data;
import org.springframework.http.HttpHeaders;

@Data
public class LoginHeaders {
    /**
     * "f" stands for "final"
     * and it help us to read UpperCase header names
     * "fContent_Type" looks better than "content-Type"
     * all headers what we send in "HttpHeaders" should begin with UpperCase letter,
     * that's why adding "f" is good solution
     */
    private HttpHeaders loginHeaders;
    private final String fOrigin = "http://diesel.elcat.kg";
    private final String fUpgrade_Insecure_Requests = "1";
    private final String fContent_Type = "application/x-www-form-urlencoded";
    private final String fUser_Agent = "Mozilla/5.0";
    private final String fAccept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
    private final String fReferer = "http://diesel.elcat.kg/";
    private final String fAccept_Encoding = "gzip, deflate, br";
    private final String fAccept_Language = "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3";

    public LoginHeaders(){
        this.loginHeaders = new HttpHeaders();

        loginHeaders.add("Origin", getFOrigin());
        loginHeaders.add("Upgrade-Insecure-Requests", getFUpgrade_Insecure_Requests());
        loginHeaders.add("Content-type", getFContent_Type());
        loginHeaders.add("DieselAccount-Agent", getFUser_Agent());
        loginHeaders.add("Accept", getFAccept());
        loginHeaders.add("Referer", getFReferer());
        loginHeaders.add("Accept-Encoding", getFAccept_Encoding());
        loginHeaders.add("Accept-Language", getFAccept_Language());

    }
}
