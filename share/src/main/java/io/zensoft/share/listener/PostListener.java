package io.zensoft.share.listener;

import io.zensoft.share.model.PostDto;

public interface PostListener {
    void publish(PostDto postDto);
}
