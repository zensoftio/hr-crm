package io.zensoft.share.service;


/**
 * Created by temirlan on 7/3/18.
 */
public interface ResponseSenderService<T> {

    void respond(T t);
}
