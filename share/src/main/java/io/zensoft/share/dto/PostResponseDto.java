package io.zensoft.share.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

    private Long postDtoId;

    private String message;
    private String url;

    private String status;

    private String postServiceType;

    private Date publishDate;
}