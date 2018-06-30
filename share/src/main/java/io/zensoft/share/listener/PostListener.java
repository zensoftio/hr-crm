package io.zensoft.share.listener;

import io.zensoft.share.dto.PostDto;

public interface PostListener {
    void publish(PostDto postDto);
}