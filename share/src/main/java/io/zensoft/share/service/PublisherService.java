package io.zensoft.share.service;

import io.zensoft.share.dto.PostDto;
import org.springframework.stereotype.Service;

@Service
public interface PublisherService {
    void publish(PostDto postDto);
}