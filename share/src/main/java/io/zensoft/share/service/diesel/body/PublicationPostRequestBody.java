package io.zensoft.share.service.diesel.body;

import io.zensoft.share.model.Vacancy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@Component
public class PublicationPostRequestBody {
    private String Post;
    private String TopicTitle;
    private String auth_key;
    private String s;

    private PublicationVacancyContentBuilder publicationVacancyContentBuilder;

    @Autowired
    public PublicationPostRequestBody(PublicationVacancyContentBuilder publicationVacancyContentBuilder) {
        this.publicationVacancyContentBuilder = publicationVacancyContentBuilder;
    }

    public LinkedMultiValueMap createBodyOfRequestInMap(Vacancy vacancy, String sessionId, String auth_key) {
        publicationVacancyContentBuilder.prepareGivenVacancyToHtmlStyle(vacancy);

        TopicTitle = publicationVacancyContentBuilder.getTitleOfPost();
        Post = publicationVacancyContentBuilder.getContentOfPost();

        MultiValueMap<String, String> bodyOfRequest = new LinkedMultiValueMap<>();
        bodyOfRequest.add("FILE_UPLOAD", "");
        bodyOfRequest.add("Post", getPost());
        bodyOfRequest.add("TopicTitle", getTopicTitle());
        bodyOfRequest.add("_from", "");
        bodyOfRequest.add("app", "forums");
        bodyOfRequest.add("attach_post_key", "fe5d84771d82c5c22bf64918212a5428");
        bodyOfRequest.add("auth_key", auth_key);
        bodyOfRequest.add("do", "new_post_do");
        bodyOfRequest.add("dosubmit", "Создать тему");
        bodyOfRequest.add("enableemo", "yes");
        bodyOfRequest.add("enablesig", "yes");
        bodyOfRequest.add("f", "243");
        bodyOfRequest.add("ipsTags", "");
        bodyOfRequest.add("isRte", "1");
        bodyOfRequest.add("module", "post");
        bodyOfRequest.add("noCKEditor_editor_5b334570b02ee", "0");
        bodyOfRequest.add("noSmilies", "0");
        bodyOfRequest.add("p", "0");
        bodyOfRequest.add("parent_id", "0");
        bodyOfRequest.add("poll_question", "");
        bodyOfRequest.add("removeattachid", "0");
        bodyOfRequest.add("return", "");
        bodyOfRequest.add("s", sessionId);
        bodyOfRequest.add("section", "post");
        bodyOfRequest.add("st", "0");
        bodyOfRequest.add("t", "");
        return (LinkedMultiValueMap) bodyOfRequest;
    }
}
