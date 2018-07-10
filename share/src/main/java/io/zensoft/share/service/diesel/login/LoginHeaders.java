package io.zensoft.share.service.diesel.login;

import io.zensoft.share.model.diesel.DefaultHeadersValues;
import lombok.Data;
import org.springframework.http.HttpHeaders;

@Data
public class LoginHeaders {
    private HttpHeaders loginHeaders;
    private DefaultHeadersValues defaultHeadersValues;

    public LoginHeaders() {
        this.loginHeaders = new HttpHeaders();

        loginHeaders.add("Origin", defaultHeadersValues.getORIGIN());
        loginHeaders.add("Upgrade-Insecure-Requests", defaultHeadersValues.getUPGRADE_INSECURE_REQUESTS());
        loginHeaders.add("Content-type", defaultHeadersValues.getCONTENT_TYPE());
        loginHeaders.add("User-Agent", defaultHeadersValues.getUSER_AGENT());
        loginHeaders.add("Accept", defaultHeadersValues.getACCEPT());
        loginHeaders.add("Referer", defaultHeadersValues.getREFERER_LOGIN());
        loginHeaders.add("Accept-Language", defaultHeadersValues.getACCEPT_LANGUAGE());
    }
}
