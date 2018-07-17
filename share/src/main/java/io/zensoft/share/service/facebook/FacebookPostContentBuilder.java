package io.zensoft.share.service.facebook;

import io.zensoft.share.model.Requirement;
import io.zensoft.share.model.RequirementType;
import io.zensoft.share.model.Vacancy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FacebookPostContentBuilder {

    public String getContent(Vacancy vacancy) {
        log.info("starting to build post content to Facebook");
        String content = vacancy.getTitle() +
                "\n" +
                getRequirements(vacancy) +
                "\n" +
                getResponsibilities() +
                "\n" +
                getSalary(vacancy) +
                "\n" +
                getWorkConditions(vacancy) +
                "\n" +
                getContactInfo(vacancy) +
                "\n" +
                getTags();
        log.info("returning built content of the Facebook post");
        return content;
    }
    private String getRequirements (Vacancy vacancy) {
        log.info("building requirements in Facebook post");
        StringBuilder stringBuilder = new StringBuilder(RequirementType.REQUIRED.getText() + ":\n");
        stringBuilder.append(getRequirementsByType(vacancy, RequirementType.REQUIRED));

        stringBuilder.append("\n" + RequirementType.GENERAL.getText() + ":\n");
        stringBuilder.append(getRequirementsByType(vacancy, RequirementType.GENERAL));

        stringBuilder.append("\n" + RequirementType.OPTIONAL.getText() + ":\n");
        stringBuilder.append(getRequirementsByType(vacancy, RequirementType.OPTIONAL));

        log.info("returning built requirements in Facebook post");
        return stringBuilder.toString();
    }

    private String getRequirementsByType(Vacancy vacancy, RequirementType requirementType) {
        log.info("building requirements of certain RequirementsType in Facebook post");
        StringBuilder stringBuilder = new StringBuilder();
        vacancy.getRequirements().forEach((Requirement requirement) -> {
            if (requirement.getType() == requirementType) {
                stringBuilder.append("• " + requirement.getName() + "\n");
            }
            log.info("returning built requirements in Facebook post");
        });
        return stringBuilder.toString();
    }

    private String getResponsibilities () {
        StringBuilder stringBuilder = new StringBuilder("Обязанности:\n");
        stringBuilder.append("• Вам предстоит заниматься разработкой долгосрочных стартап-проектов, которые развиваются на протяжении уже многих лет и являются успешными в своем направлении.");
        return stringBuilder.toString();
    }

    private String getSalary(Vacancy vacancy) {
        log.info("building salary fork in Facebook post");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Вилка заработной платы для " + vacancy.getPosition() + ":");
        stringBuilder.append(" $" + vacancy.getSalaryMin() + " - $"+ vacancy.getSalaryMax() + "\n");
        log.info("returning built salary fork in Facebook post");
        return  stringBuilder.toString();
    }

    private String getWorkConditions(Vacancy vacancy) {
        log.info("building working conditions in Facebook post");
        StringBuilder stringBuilder = new StringBuilder("Условия работы\n");
        vacancy.getWorkConditions().forEach((String workCondition) -> stringBuilder.append("• " + workCondition + "\n"));
        log.info("returning built working conditions in Facebook post");
        return stringBuilder.toString();
    }

    private String getContactInfo(Vacancy vacancy) {
        log.info("building contact information in Facebook post");
        StringBuilder stringBuilder = new StringBuilder("Резюме присылать на почту: jobs@zensoft.kg\n");
        stringBuilder.append("!! В теме обязательно укажите позицию, на которую претендуете:\n");
        stringBuilder.append("\"" + vacancy.getPosition() + "\"");
        log.info("returning built contact information in Facebook post");
        return stringBuilder.toString();
    }

    private String getTags() {
        log.info("building tags in Facebook post");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#бишкек #вакансия #программирование\n");
        stringBuilder.append("#zensoft #devops #continiousintegration #continiousdelivery #docker #k8s #network");
        log.info("returning built tags in Facebook post");
        return stringBuilder.toString();
    }
}