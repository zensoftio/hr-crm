package io.zensoft.share.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VacancyDto {
    private Long id;
    private String title;
    private String sphere;                          //"Интернет, IT, телеком, связь"
    private String description;

    private List<String> competencies;
    private List<String> requirements;
    private List<String> responsibilities;
    private List<String> workingConditions;

    private Long salaryMin;
    private Long salaryMax;
    private String additionalInfo;

    private List<String> links;

    private String imageUrl;
    private String education;
    private String schedule;
    private String city;

}