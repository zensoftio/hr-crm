package io.zensoft.share.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class PostDto {
    @Id
    private Long id;

    private String title;
    private String sphere;                          //"Интернет, IT, телеком, связь"
    private String description;

    @ElementCollection
    @CollectionTable(name = "post_competency", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "competency_name")
    private List<String> competencies;

    @ElementCollection
    @CollectionTable(name = "post_requirements", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "requirement_name")
    private List<String> requirements;

    @ElementCollection
    @CollectionTable(name = "post_responsibility", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "responsibility_name")
    private List<String> responsibilities;

    @ElementCollection
    @CollectionTable(name = "post_work_condition", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "work_condition_name")
    private List<String> workingConditions;

    private Long salaryMin;
    private Long salaryMax;
    private String additionalInfo;

    @ElementCollection
    @CollectionTable(name = "post_links", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "link")
    private List<String> links;

    private String imageUrl;
    private String education;
    private String schedule;
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSphere() {
        return sphere;
    }

    public void setSphere(String sphere) {
        this.sphere = sphere;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(List<String> competencies) {
        this.competencies = competencies;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public List<String> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public List<String> getWorkingConditions() {
        return workingConditions;
    }

    public void setWorkingConditions(List<String> workingConditions) {
        this.workingConditions = workingConditions;
    }

    public Long getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Long salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Long getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Long salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}