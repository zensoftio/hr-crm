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
    private int uid;
    private String title;
    @OneToMany(mappedBy = "vacancy")
    private List<Requirement> requirements;
    private String city;
    private String address;
    private String position;
    private int count;
    @ElementCollection
    @CollectionTable(name = "vacancy_working_condition", joinColumns = @JoinColumn(name = "vacancy_id"))
    @Column(name = "name")
    private List<String> workingConditions;
    private String experience;
    private String workingHours;
    private String employmentType;
    private int salaryMin;
    private int salaryMax;
    private String image;
}