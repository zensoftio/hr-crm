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
    private String uuid;
    private String title;
    @OneToMany(mappedBy = "vacancy")
    private List<Requirement> requirements;
    private String city;
    private String address;
    private String position;
    private Integer count;
    @ElementCollection
    @CollectionTable(name = "vacancy_work_condition", joinColumns = @JoinColumn(name = "vacancy_id"))
    @Column(name = "name")
    private List<String> workConditions;
    @Enumerated(value = EnumType.STRING)
    private WorkingHours workingHours;
    private Integer salaryMin;
    private Integer salaryMax;
    private String image;
    private String responsibilities;
    private String comment;
}
