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
    private int uid;
    private String title;
    private List<RequirementDto> requirements;
    private String city;
    private String address;
    private String position;
    private int count;
    private String workingConditions;
    private String experience;
    private String workingHours;
    private String employmentType;
    private int salaryMin;
    private int salaryMax;
    private String image;
    private String facebookUserAccessToken;
}