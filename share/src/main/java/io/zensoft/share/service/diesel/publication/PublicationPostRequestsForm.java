package io.zensoft.share.service.diesel.publication;

import io.zensoft.share.model.Vacancy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
public class PublicationPostRequestsForm {

    /**
     * names of fields are same with required fields in POST Request
     * <p>
     * "f" stands for "final"
     * and it help us to read UpperCase header names
     * "fFILE_UPLOAD" looks better than "fILE_UPLOAD"
     * all headers what we send in "HttpHeaders" should begin with UpperCase letter,
     * that's why adding "f" is good solution
     */
    private final String fFILE_UPLOAD = "";
    private final String f_from = "";
    private final String fapp = "forums";
    private final String fattach_post_key = "fe5d84771d82c5c22bf64918212a5428";
    private final String fdo = "new_post_do";
    private final String fdosubmit = "Создать тему";
    private final String fenableemo = "yes";
    private final String fenablesig = "yes";
    private final String ff = "243";
    private final String fipsTags = "";
    private final String fisRte = "1";
    private final String fmodule = "post";
    private final String fnoCKEditor_editor_5b334570b02ee = "0";
    private final String fnoSmilies = "0";
    private final String fp = "0";
    private final String fparent_id = "0";
    private final String fpoll_question = "";
    private final String fremoveattachid = "0";
    private final String freturn = "";
    private final String fsection = "post";
    private final String fst = "0";
    private final String ft = "";

    /**
     * this fields are not final, they can be changed
     */
    private String Post;
    private String TopicTitle;
    private String auth_key;
    private String s;

    private MultiValueMap<String, String> bodyOfRequest;

    @Autowired
    private PublicationVacancyContentPreparer publicationVacancyContentPreparer;

    public PublicationPostRequestsForm() {
        bodyOfRequest = new LinkedMultiValueMap<>();
        publicationVacancyContentPreparer = new PublicationVacancyContentPreparer();
    }

    public LinkedMultiValueMap createBodyOfRequestInMap(Vacancy vacancy, String sessionId, String auth_key) {
        publicationVacancyContentPreparer.prepareGivenVacancyToHtmlStyle(vacancy);

        TopicTitle = publicationVacancyContentPreparer.getTitleOfPost();
        Post = publicationVacancyContentPreparer.getContentOfPost();

        bodyOfRequest.add("FILE_UPLOAD", getFFILE_UPLOAD());
        bodyOfRequest.add("Post", getPost());
        bodyOfRequest.add("TopicTitle", getTopicTitle());
        bodyOfRequest.add("_from", getF_from());
        bodyOfRequest.add("app", getFapp());
        bodyOfRequest.add("attach_post_key", getFattach_post_key());
        bodyOfRequest.add("auth_key", auth_key);
        bodyOfRequest.add("do", getFdo());
        bodyOfRequest.add("dosubmit", getFdosubmit());
        bodyOfRequest.add("enableemo", getFenableemo());
        bodyOfRequest.add("enablesig", getFenablesig());
        bodyOfRequest.add("f", getFf());
        bodyOfRequest.add("ipsTags", getFipsTags());
        bodyOfRequest.add("isRte", getFisRte());
        bodyOfRequest.add("module", getFmodule());
        bodyOfRequest.add("noCKEditor_editor_5b334570b02ee", getFnoCKEditor_editor_5b334570b02ee());
        bodyOfRequest.add("noSmilies", getFnoSmilies());
        bodyOfRequest.add("p", getFp());
        bodyOfRequest.add("parent_id", getFparent_id());
        bodyOfRequest.add("poll_question", getFpoll_question());
        bodyOfRequest.add("removeattachid", getFremoveattachid());
        bodyOfRequest.add("return", getFreturn());
        bodyOfRequest.add("s", sessionId);
        bodyOfRequest.add("section", getFsection());
        bodyOfRequest.add("st", getFst());
        bodyOfRequest.add("t", getFt());
        return (LinkedMultiValueMap) bodyOfRequest;
    }
}
