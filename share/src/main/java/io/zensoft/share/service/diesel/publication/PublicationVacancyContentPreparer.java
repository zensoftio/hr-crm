package io.zensoft.share.service.diesel.publication;

import io.zensoft.share.model.Requirement;
import io.zensoft.share.model.Vacancy;
import lombok.Data;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Component;
import java.io.StringWriter;

@Data
@Component
public class PublicationVacancyContentPreparer {
    private String titleOfPost;
    private String contentOfPost;

    private final String MAIN_REQUIREMENTS_TITLE = "Основные требования:";
    private final String HARD_SKILLS_TITLE = "Владение / наличие следующих навыков и знаний определят Ваш квалификационный уровень (Junior, Middle, Senior):";
    private final String ADDITIONAL_REQUIREMENTS_TITLE = "Будет плюсом:";
    private final String RESPONSIBILITIES_TITLE = "Обязанности:";
    private final String RESPONSIBILITIES_DESCRIPTION = "• Вам предстоит заниматься разработкой долгосрочных стартап-проектов, которые развиваются на протяжении уже многих лет и являются успешными в своем направлении.";
    private final String WORKING_CONDITIONS_TITLE = "Условия работы:";
    private final String SALARY_FORK_TITLE = "Вилка заработной платы: $";
    private final String CV_SENDING_TITLE = "Резюме присылать на почту: jobs@secondlab.kg";
    private final String SET_POSITION_IN_EMAIL_DISCLAIMER_TITLE = "!! В теме обязательно укажите позицию, на которую претендуете: \"Java Developer (Junior, Middle, Senior)\"";
    private final String INFORMATION_ABOUT_US = "<strong>Информация о нас:";
    private final String WEBSITE_LINK = "http://secondlab.io";
    private final String FACEBOOK_LINK = "https://www.facebook.com/secodlab.io";
    private final String LINKEDIN_LINK = "https://www.linkedin.com/company-beta/226233/";
    private final String INSTAGRAM_LINK = "https://www.instagram.com/secondlab.kg/";
    private final String DOT_SYMBOL = "• ";
    private final String DOLLAR_SIGN = "- $";

    String mainRequirementsParsed;
    String hardSkillsParsed;
    String additionalSkillsParsed;
    String workingConditionsParsed;

    public void prepareGivenVacancyToHtmlStyle(Vacancy vacancy){
        sortVacanyListsbyType(vacancy);
        setTitleOfPost(vacancy.getPosition());

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        Template template = velocityEngine.getTemplate("/src/main/resources/html/vacancyContentTemplate.vt", "utf-8");
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("mainRequirementsTitle", MAIN_REQUIREMENTS_TITLE);
        velocityContext.put("hardSkillsTitle", HARD_SKILLS_TITLE);
        velocityContext.put("additionalRequirementsTitle", ADDITIONAL_REQUIREMENTS_TITLE);
        velocityContext.put("responsibilitiesTitle", RESPONSIBILITIES_TITLE);
        velocityContext.put("responsibilitiesDescription", RESPONSIBILITIES_DESCRIPTION);
        velocityContext.put("workingConditionsTitle", WORKING_CONDITIONS_TITLE);
        velocityContext.put("salaryForkTitle", SALARY_FORK_TITLE);
        velocityContext.put("cvSendingTitle", CV_SENDING_TITLE);
        velocityContext.put("setPositionInEmailDisclaimerTitle", SET_POSITION_IN_EMAIL_DISCLAIMER_TITLE);
        velocityContext.put("informationAboutUs", INFORMATION_ABOUT_US);
        velocityContext.put("websiteLink", WEBSITE_LINK);
        velocityContext.put("facebookLink", FACEBOOK_LINK);
        velocityContext.put("linkedinLink", LINKEDIN_LINK);
        velocityContext.put("instagramLink", INSTAGRAM_LINK);
        velocityContext.put("dotSymbol", DOT_SYMBOL);
        velocityContext.put("dollarSign", DOLLAR_SIGN);

        velocityContext.put("mainRequirementsParsed", mainRequirementsParsed);
        velocityContext.put("hardSkillsParsed", hardSkillsParsed);
        velocityContext.put("additionalSkillsParsed", additionalSkillsParsed);
        velocityContext.put("workingConditionsParsed", workingConditionsParsed);
        velocityContext.put("vacancyTitle", vacancy.getTitle());
        velocityContext.put("vacancySalaryMin", vacancy.getSalaryMin());
        velocityContext.put("vacancySalaryMax", vacancy.getSalaryMax());

        StringWriter writer = new StringWriter();
        template.merge(velocityContext, writer);

        setContentOfPost(writer.toString());
    }

    public void sortVacanyListsbyType(Vacancy vacancy) {
        for (Requirement requirementOrSkill : vacancy.getRequirements()) {
            if (requirementOrSkill.getType() == 0) {
                mainRequirementsParsed = mainRequirementsParsed + DOT_SYMBOL + requirementOrSkill.getName() + "<br>";
            }
            if (requirementOrSkill.getType() == 1) {
                hardSkillsParsed = hardSkillsParsed + DOT_SYMBOL + requirementOrSkill.getName() + "<br>";
            }
            if (requirementOrSkill.getType() == 2) {
                additionalSkillsParsed = additionalSkillsParsed + DOT_SYMBOL + requirementOrSkill.getName() + "<br>";
            }
        }
        for (String workingCondition : vacancy.getWorkingConditions()) {
            workingConditionsParsed = workingConditionsParsed + DOT_SYMBOL + workingCondition + "<br>";
        }
    }
}