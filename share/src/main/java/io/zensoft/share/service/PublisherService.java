package io.zensoft.share.service;

import io.zensoft.share.model.PostDto;
import io.zensoft.share.model.PostResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PublisherService {

    PostResponseDto publish(PostDto postDto);

}
