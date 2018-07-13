package io.zensoft.share.service.diesel.login;

import io.zensoft.share.model.diesel.DefaultHeadersValues;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginHeaders {
    private HttpHeaders loginHeaders;
    private DefaultHeadersValues defaultHeadersValues;

    @Autowired
    public LoginHeaders(DefaultHeadersValues defaultHeadersValues) {
        loginHeaders = new HttpHeaders();
        this.defaultHeadersValues = defaultHeadersValues;
        loginHeaders.add("Origin", defaultHeadersValues.getORIGIN());
        loginHeaders.add("Upgrade-Insecure-Requests", defaultHeadersValues.getUPGRADE_INSECURE_REQUESTS());
        loginHeaders.add("Content-type", defaultHeadersValues.getCONTENT_TYPE());
        loginHeaders.add("User-Agent", defaultHeadersValues.getUSER_AGENT());
        loginHeaders.add("Accept", defaultHeadersValues.getACCEPT());
        loginHeaders.add("Referer", defaultHeadersValues.getREFERER_LOGIN());
        loginHeaders.add("Accept-Encoding", defaultHeadersValues.getACCEPT_ENCODING());
        loginHeaders.add("Accept-Language", defaultHeadersValues.getACCEPT_LANGUAGE());
    }
}
