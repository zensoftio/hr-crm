package io.zensoft.share.service.diesel.header;

import io.zensoft.share.service.diesel.header.HeaderValue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginHeader {
    private HttpHeaders loginHeaders;
    private HeaderValue headerValue;

    @Autowired
    public LoginHeader(HeaderValue headerValue) {
        loginHeaders = new HttpHeaders();
        this.headerValue = headerValue;
        loginHeaders.add("Origin", headerValue.getORIGIN());
        loginHeaders.add("Upgrade-Insecure-Requests", headerValue.getUPGRADE_INSECURE_REQUESTS());
        loginHeaders.add("Content-type", headerValue.getCONTENT_TYPE());
        loginHeaders.add("User-Agent", headerValue.getUSER_AGENT());
        loginHeaders.add("Accept", headerValue.getACCEPT());
        loginHeaders.add("Referer", headerValue.getREFERER_LOGIN());
        loginHeaders.add("Accept-Encoding", headerValue.getACCEPT_ENCODING());
        loginHeaders.add("Accept-Language", headerValue.getACCEPT_LANGUAGE());
    }
}
