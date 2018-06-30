package io.zensoft.share.service.model.impl;

import io.zensoft.share.model.Post;
import io.zensoft.share.service.model.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 6/29/18.
 */
@Service
public class PostServiceImpl extends AbstractModelServiceImpl<Post, Long> implements PostService {
    @Autowired
    public PostServiceImpl(JpaRepository<Post, Long> repository) {
        super(repository);
    }
}
