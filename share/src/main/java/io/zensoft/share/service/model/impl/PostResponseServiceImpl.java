package io.zensoft.share.service.model.impl;

import io.zensoft.share.model.PostResponse;
import io.zensoft.share.service.model.PostResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 6/29/18.
 */
@Service
public class PostResponseServiceImpl extends AbstractModelServiceImpl<PostResponse, Long> implements PostResponseService {
    @Autowired
    public PostResponseServiceImpl(JpaRepository<PostResponse, Long> repository) {
        super(repository);
    }
}
