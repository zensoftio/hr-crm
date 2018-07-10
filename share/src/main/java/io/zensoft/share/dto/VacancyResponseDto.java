package io.zensoft.share.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VacancyResponseDto {

    private int uid;

    private String message;
    private String url;

    private String status;

    private String publisherServiceType;

    private Date publishDate;
}