package io.zensoft.share.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by temirlan on 7/5/18.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Requirement {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer type;
    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
}
