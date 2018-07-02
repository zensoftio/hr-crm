package io.zensoft.share.service;

import io.zensoft.share.dto.PostDto;
import io.zensoft.share.model.Post;
import io.zensoft.share.model.PostResponse;
import io.zensoft.share.service.model.PostResponseService;
import io.zensoft.share.service.model.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public abstract class BasePublisherService implements PublisherService {
    protected final RestTemplate restTemplate;
    private final PostService postService;
    private final PostResponseService postResponseService;

    @Autowired
    public BasePublisherService(RestTemplate restTemplate, PostService postService, PostResponseService postResponseService) {
        this.restTemplate = restTemplate;
        this.postService = postService;
        this.postResponseService = postResponseService;
    }

    @Override
    public void publish(PostDto postDto) {
        Post post = convertDto(postDto);
        postService.save(post);
        PostResponse postResponse = publish(post);
        postResponseService.save(postResponse);
        //after send response to monolith
    }

    //TODO
    private Post convertDto(PostDto postDto) {
        Post post = new Post();
        return post;
    }

    public abstract PostResponse publish(Post post);
}
