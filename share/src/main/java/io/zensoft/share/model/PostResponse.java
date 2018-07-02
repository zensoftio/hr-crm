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
public class PostResponse {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn()
    private Post post;

    private String message;
    private String url;

    @Enumerated(value = EnumType.STRING)
    private PostStatus status;

    @Enumerated(value = EnumType.STRING)
    private PostServiceType postServiceType;

    private Date publishDate;
}
