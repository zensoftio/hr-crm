package io.zensoft.share.service.jobkg.auth;

import io.zensoft.share.config.jobkg.JobKgUserProperties;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 7/12/18.
 */
@Service
public interface JobKgAuthorizationService {
    //returns cookie
    String authorize(JobKgUserProperties user) throws AuthorizationFailedException;

    //returns cookie
    String authorize() throws AuthorizationFailedException;
}