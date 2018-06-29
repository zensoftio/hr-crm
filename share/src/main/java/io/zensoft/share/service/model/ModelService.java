package io.zensoft.share.service.model;

/**
 * Created by temirlan on 6/29/18.
 */
public interface ModelService<ENTITY, ID> {

    void save(ENTITY entity);
}
