package io.zensoft.share.model.builder;

import io.zensoft.share.model.Vacancy;
import io.zensoft.share.model.Vacancy;

import java.util.List;

/**
 * Created by temirlan on 7/2/18.
 */
public class VacancyBuilder {
    private Vacancy vacancy;

    public VacancyBuilder() {
        vacancy = new Vacancy();
    }


    public VacancyBuilder setId(Long id) {
        vacancy.setId(id);
        return this;
    }

    public VacancyBuilder setVacancyId(Long vacancyId) {
        vacancy.setVacancyId(vacancyId);
        return this;
    }

    public VacancyBuilder setTitle(String title) {
        vacancy.setTitle(title);
        return this;
    }

    public VacancyBuilder setSphere(String sphere) {
        vacancy.setSphere(sphere);
        return this;
    }

    public VacancyBuilder setDescription(String description) {
        vacancy.setDescription(description);
        return this;
    }

    public VacancyBuilder setCompetencies(List<String> competencies) {
        vacancy.setCompetencies(competencies);
        return this;
    }

    public VacancyBuilder setRequirements(List<String> requirements) {
        vacancy.setRequirements(requirements);
        return this;
    }

    public VacancyBuilder setResponsibilities(List<String> responsibilities) {
        vacancy.setResponsibilities(responsibilities);
        return this;
    }

    public VacancyBuilder setWorkingConditions(List<String> workingConditions) {
        vacancy.setWorkingConditions(workingConditions);
        return this;
    }

    public VacancyBuilder setSalaryMin(Long salaryMin) {
        vacancy.setSalaryMin(salaryMin);
        return this;
    }

    public VacancyBuilder setSalaryMax(Long salaryMax) {
        vacancy.setSalaryMax(salaryMax);
        return this;
    }

    public VacancyBuilder setAdditionalInfo(String additionalInfo) {
        vacancy.setAdditionalInfo(additionalInfo);
        return this;
    }

    public VacancyBuilder setLinks(List<String> links) {
        vacancy.setLinks(links);
        return this;
    }

    public VacancyBuilder setImageUrl(String imageUrl) {
        vacancy.setImageUrl(imageUrl);
        return this;
    }

    public VacancyBuilder setEducation(String education) {
        vacancy.setEducation(education);
        return this;
    }

    public VacancyBuilder setSchedule(String schedule) {
        vacancy.setSchedule(schedule);
        return this;
    }

    public VacancyBuilder setCity(String city) {
        vacancy.setCity(city);
        return this;
    }

    public Vacancy build() {
        return vacancy;
    }
}
