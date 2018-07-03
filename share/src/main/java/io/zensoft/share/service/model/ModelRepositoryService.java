package io.zensoft.share.service.model;

/**
 * Created by temirlan on 6/29/18.
 */
public interface ModelRepositoryService<ENTITY, ID> {

    void save(ENTITY entity);
}
