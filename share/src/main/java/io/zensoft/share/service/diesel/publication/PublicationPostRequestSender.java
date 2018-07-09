package io.zensoft.share.service.diesel.publication;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.diesel.PublicationHeaders;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Data
@Service
public class PublicationPostRequestSender {

    private String server;

    @Autowired
    private PublicationHeaders publicationHeaders;

    @Autowired
    private PublicationPostRequestsForm publicationPostRequestsForm;

    @Autowired
    private PublicationVacancyContentPreparer publicationVacancyContentPreparer;

    public PublicationPostRequestSender(){
        server = "http://diesel.elcat.kg/index.php?";
    }

    /**
     * sends POST request to "servert" with headers and body
     * @param restTemplate
     * @param vacancy
     * @param sessionId
     */

    public void sendPostRequestForPublication(RestTemplate restTemplate, Vacancy vacancy, String sessionId, String authKey) {
        HttpEntity<?> request = new HttpEntity<>(publicationPostRequestsForm.createBodyOfRequestInMap(vacancy, sessionId, authKey), publicationHeaders.getPublicationHeaders());

        ResponseEntity<String> response = restTemplate.postForEntity(server, request, String.class);
    }

    /**
     * adds cookie one more header in headers.
     *
     * @param sessionId
     */
    public void addCookieToHeaders(String sessionId){
        publicationHeaders.getPublicationHeaders().add("Cookie", "coppa=0; _ga=GA1.2.1282133015.1530688724; _gid=GA1.2.1338586460.1530688724; astratop=1; member_id=0; pass_hash=0; _ga=GA1.2.1282133015.1530688724; _gid=GA1.2.1338586460.1530688724; member_id=0; pass_hash=0; ipsconnect_5ec502fd645bc2f7cc566fb6fdf81f7a=0; _gat=1; ipsconnect_5ec502fd645bc2f7cc566fb6fdf81f7a=1; rteStatus=rte; modtids=%2C; mqtids=%2C; itemMarking_forums_items=eJw9yLERwDAIA8BdXFynwICkkNV83t2Fj3z5a3i5o17E-CbCZCnY053qjhC7lbjNKml2k3-Tyn1cMHohEw8%2C; session_id="+ sessionId);
    }
}
