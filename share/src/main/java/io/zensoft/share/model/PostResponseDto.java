package io.zensoft.share.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PostResponseDto {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn()
    private PostDto postDto;

    private String message;
    private String url;

    @Enumerated(value = EnumType.STRING)
    private PostStatus status;

    @Enumerated(value = EnumType.STRING)
    private PostServiceType postServiceType;

    private Date publishDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostDto getPostDto() {
        return postDto;
    }

    public void setPostDto(PostDto postDto) {
        this.postDto = postDto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public PostServiceType getPostServiceType() {
        return postServiceType;
    }

    public void setPostServiceType(PostServiceType postServiceType) {
        this.postServiceType = postServiceType;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
