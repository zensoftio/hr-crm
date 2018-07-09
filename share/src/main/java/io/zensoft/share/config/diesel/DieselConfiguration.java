package io.zensoft.share.config.diesel;

import io.zensoft.share.model.diesel.AuthKeyGetterHeaders;
import io.zensoft.share.model.diesel.DieselAccount;
import io.zensoft.share.model.diesel.LoginHeaders;
import io.zensoft.share.model.diesel.PublicationHeaders;
import io.zensoft.share.service.diesel.login.LoginPostRequestsForm;
import io.zensoft.share.service.diesel.publication.PublicationPostRequestsForm;
import io.zensoft.share.service.diesel.publication.PublicationVacancyContentPreparer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:account.properties")
public class DieselConfiguration {
    @Value("${diesel.username}")
    private String username;
    @Value("${diesel.password}")
    private String password;

    @Bean
    public DieselAccount getDieselAccount() {
        return new DieselAccount(username,password);
    }

    @Bean
    public PublicationHeaders getPublicationHeaders() {
        return new PublicationHeaders();
    }

    @Bean
    public LoginHeaders getLoginHeaders() {
        return new LoginHeaders();
    }

    @Bean
    public AuthKeyGetterHeaders getAuthKeyGetterHeaders(){
        return new AuthKeyGetterHeaders();
    }

    @Bean
    public LoginPostRequestsForm getLoginPostRequestsForm(){
        return new LoginPostRequestsForm();
    }

    @Bean
    public PublicationPostRequestsForm getPublicationPostRequestsForm(){
        return new PublicationPostRequestsForm();
    }

    @Bean
    public PublicationVacancyContentPreparer getPublicationVacancyContentPreparer(){
        return new PublicationVacancyContentPreparer();
    }

}
