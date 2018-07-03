package io.zensoft.share.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VacancyResponse {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn()
    private Vacancy vacancy;

    private String message;
    private String url;

    @Enumerated(value = EnumType.STRING)
    private VacancyStatus status;

    @Enumerated(value = EnumType.STRING)
    private PublisherServiceType publisherServiceType;

    private Date publishDate;

}
