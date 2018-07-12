package io.zensoft.share.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String uuid;
    private String title;
    private List<RequirementDto> requirements;
    private String city;
    private String address;
    @JsonProperty("name")
    private String position;
    private int count;
    private List<String> workConditions;
    private String workingHours;
    private int salaryMin;
    private int salaryMax;
    private String image;
    private String responsibilities;
    private String comment;
    private String facebookUserAccessToken;
}
