package io.zensoft.share.service.diesel.publication;

import io.zensoft.share.model.Requirement;
import io.zensoft.share.model.Vacancy;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
public class PublicationVacancyContentPreparer {
    /**
     * following two fields should contain title and content of Vacancy parsed in html forman
     */
    private String titleOfPost;
    private String contentOfPost;

    /**
     * following fields usually do not have to change, but not be sure
     */
    private final String mainRequirementsTitle = "<strong>Основные требования:</strong>";
    private final String hardSkillsTitle = "<strong>Владение / наличие следующих навыков и знаний определят Ваш квалификационный уровень (Junior, Middle, Senior):</strong>";
    private final String additionalRequirementsTitle = "<strong>Будет плюсом:</strong>";
    private final String responsibilitiesTitle = "<strong>Обязанности:</strong>";
    private final String responsibilitiesDescription = "• Вам предстоит заниматься разработкой долгосрочных стартап-проектов, которые развиваются на протяжении уже многих лет и являются успешными в своем направлении.";
    private final String workingConditionsTitle = "<strong>Условия работы:</strong>";
    private final String salaryForkTitle = "<span style = \"color:#800000;\"><strong>Вилка заработной платы: $";
    private final String cvSendingTitle = "<span style = \"color:#0000cd;\"><strong>Резюме присылать на почту: jobs@secondlab.kg</strong></span>";
    private final String setPositionInEmailDisclaimerTitle = "<strong>!! В теме обязательно укажите позицию, на которую претендуете: \"Java Developer (Junior, Middle, Senior)\"</strong>";
    private final String informationAboutUsTitle = "<strong>Информация о нас:</strong>";
    private final String linksToWebsitesAndSocialNetworks = "Website: <a href='http://secondlab.io' class='bbc_url' title='Ссылка' rel='nofollow external'>http://<span class='searchlite'>seconlab</span>.io</a><br>\n" +
            "Facebook: <a href='https://www.facebook.com/secondlab.io' class='bbc_url' title='Ссылка' rel='nofollow external'>https://www.facebook.com/secondlab</a><br>\n" +
            "LinkedIn: <a href='https://www.linkedin.com/company-beta/226233/' class='bbc_url' title='Ссылка' rel='nofollow external'>https://www.linkedin.com/company-beta/226133/</a><br>\n" +
            "Instagram: <a href='https://www.instagram.com/secondlab.kg/' class='bbc_url' title='Ссылка' rel='nofollow external'>https://www.instagram.com/secondlab.kg//</a></p>\n";

    /**
     * simple html tags for farmatting text
     */
    private final String brTag = "<br>";
    private final String pTagOpen = "<p>";
    private final String pTagClose = "</p>";
    private final String strongTagOpen = "<strong>";
    private final String strongTagClose = "</strong>";
    private final String dotSymbolForListing = "• ";
    private final String spanTagClose = "</span>";

    public PublicationVacancyContentPreparer() {

    }

    /**
     * takes Vacancy as parameter and reformat information as html
     *
     * @param vacancy
     */
    public void prepareGivenVacancyToHtmlStyle(Vacancy vacancy) {
        setTitleOfPost(vacancy.getPosition());

        String mainRequirementsParsed = "";
        String hardSkillsParsed = "";
        String additionalSkillsParsed = "";

        for (Requirement requirementOrSkill : vacancy.getRequirements()) {
            if (requirementOrSkill.getType() == 0) {
                mainRequirementsParsed = mainRequirementsParsed + dotSymbolForListing + requirementOrSkill.getName() + brTag;
            }

            if (requirementOrSkill.getType() == 1) {
                hardSkillsParsed = hardSkillsParsed + dotSymbolForListing + requirementOrSkill.getName() + brTag;
            }

            if (requirementOrSkill.getType() == 2) {
                additionalSkillsParsed = additionalSkillsParsed + dotSymbolForListing + requirementOrSkill.getName() + brTag;
            }
        }

        String workingConditionsParsed = "";

        for (String workingCondition : vacancy.getWorkingConditions()) {
            workingConditionsParsed = workingConditionsParsed + dotSymbolForListing + workingCondition + brTag;
        }

        String fullContentInHtmlFormat = pTagOpen
                + strongTagOpen
                + vacancy.getTitle()
                + strongTagClose
                + pTagClose
                + pTagOpen + " " + pTagClose
                + brTag
                + pTagOpen
                + mainRequirementsTitle
                + brTag
                + mainRequirementsParsed
                + brTag
                + hardSkillsTitle
                + brTag
                + hardSkillsParsed
                + pTagClose
                + pTagOpen + " " + pTagClose
                + brTag
                + pTagOpen
                + additionalRequirementsTitle
                + brTag
                + additionalSkillsParsed
                + pTagOpen + " " + pTagClose
                + brTag
                + responsibilitiesTitle
                + brTag
                + responsibilitiesDescription
                + pTagClose
                + pTagOpen + " " + pTagClose
                + brTag
                + pTagOpen
                + workingConditionsTitle
                + brTag
                + workingConditionsParsed
                + brTag
                + salaryForkTitle
                + vacancy.getSalaryMin()
                + " - $"
                + vacancy.getSalaryMax()
                + strongTagClose
                + spanTagClose
                + brTag + brTag
                + cvSendingTitle
                + brTag + brTag
                + setPositionInEmailDisclaimerTitle
                + brTag + brTag
                + informationAboutUsTitle
                + brTag
                + linksToWebsitesAndSocialNetworks
                + brTag;

        setContentOfPost(fullContentInHtmlFormat);
    }
}