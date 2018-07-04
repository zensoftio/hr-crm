package io.zensoft.share.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vacancy {
    @Id
    @GeneratedValue
    private Long id;
    private Long vacancyId;

    private String title;
    private String sphere;                          //"Интернет, IT, телеком, связь"
    private String description;

    @ElementCollection
    @CollectionTable(name = "post_competency", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "competency_name")
    private List<String> competencies;

    @ElementCollection
    @CollectionTable(name = "post_requirements", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "requirement_name")
    private List<String> requirements;

    @ElementCollection
    @CollectionTable(name = "post_responsibility", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "responsibility_name")
    private List<String> responsibilities;

    @ElementCollection
    @CollectionTable(name = "post_work_condition", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "work_condition_name")
    private List<String> workingConditions;

    private Long salaryMin;
    private Long salaryMax;
    private String additionalInfo;

    @ElementCollection
    @CollectionTable(name = "post_links", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "link")
    private List<String> links;

    private String imageUrl;
    private String education;
    private String schedule;
    private String city;

}