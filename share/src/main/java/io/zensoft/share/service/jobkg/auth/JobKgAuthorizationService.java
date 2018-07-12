package io.zensoft.share.service.jobkg.auth;

import io.zensoft.share.model.jobkg.JobKgUser;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 7/12/18.
 */
@Service
public interface JobKgAuthorizationService {
    //returns cookie
    String authorize(JobKgUser user) throws AuthorizationFailedException;

    //returns cookie
    String authorize() throws AuthorizationFailedException;
}