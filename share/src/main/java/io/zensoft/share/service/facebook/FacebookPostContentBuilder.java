package io.zensoft.share.service.facebook;

import io.zensoft.share.model.Requirement;
import io.zensoft.share.model.RequirementType;
import io.zensoft.share.model.Vacancy;
import org.springframework.stereotype.Service;

@Service
public class FacebookPostContentBuilder {

    public String getContent(Vacancy vacancy) {
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
                getContactsForFeedback(vacancy) +
                "\n" +
                getTags();
        return content;
    }
    private String getRequirements (Vacancy vacancy) {
        StringBuilder stringBuilder = new StringBuilder("Основные требования:\n");
        stringBuilder.append(getRequirementsByType(vacancy, RequirementType.REQUIRED));

        stringBuilder.append("\nВладение / наличие следующих навыков и знаний определят Ваш квалификационный уровень (Junior, Middle, Senior):\n");
        stringBuilder.append(getRequirementsByType(vacancy, RequirementType.GENERAL));

        stringBuilder.append("\nБудет плюсом:\n");
        stringBuilder.append(getRequirementsByType(vacancy, RequirementType.OPTIONAL));

        return stringBuilder.toString();
    }

    private String getRequirementsByType(Vacancy vacancy, RequirementType requirementType) {
        StringBuilder stringBuilder = new StringBuilder();
        vacancy.getRequirements().forEach((Requirement requirement) -> {
            if (requirement.getType() == requirementType) {
                stringBuilder.append("• " + requirement.getName() + "\n");
            }
        });
        return stringBuilder.toString();
    }

    private String getResponsibilities () {
        StringBuilder stringBuilder = new StringBuilder("Обязанности:\n");
        stringBuilder.append("• Вам предстоит заниматься разработкой долгосрочных стартап-проектов, которые развиваются на протяжении уже многих лет и являются успешными в своем направлении.");
        return stringBuilder.toString();
    }

    private String getSalary(Vacancy vacancy) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Вилка заработной платы для " + vacancy.getPosition() + ":");
        stringBuilder.append(" $" + vacancy.getSalaryMin() + " - $"+ vacancy.getSalaryMax() + "\n");
        return  stringBuilder.toString();
    }

    private String getWorkConditions(Vacancy vacancy) {
        StringBuilder stringBuilder = new StringBuilder("Условия работы\n");
        vacancy.getWorkConditions().forEach((String workCondition) -> stringBuilder.append("• " + workCondition + "\n"));
        return stringBuilder.toString();
    }

    private String getContactsForFeedback(Vacancy vacancy) {
        StringBuilder stringBuilder = new StringBuilder("Резюме присылать на почту: jobs@zensoft.kg\n");
        stringBuilder.append("!! В теме обязательно укажите позицию, на которую претендуете:\n");
        stringBuilder.append("\"" + vacancy.getPosition() + "\"");
        return stringBuilder.toString();
    }

    private String getTags() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#бишкек #вакансия #программирование\n");
        stringBuilder.append("#zensoft #devops #continiousintegration #continiousdelivery #docker #k8s #network");
        return stringBuilder.toString();
    }
}