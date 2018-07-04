package io.zensoft.share.service.model.impl;


import io.zensoft.share.service.model.ModelRepositoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by temirlan on 6/29/18.
 */
@Service
public abstract class AbstractModelRepositoryServiceImpl<ENTITY, ID> implements ModelRepositoryService<ENTITY, ID> {

    protected JpaRepository<ENTITY, ID> repository;

    public AbstractModelRepositoryServiceImpl(JpaRepository<ENTITY, ID> repository) {
        this.repository = repository;
    }

    @Override
    public void save(ENTITY entity) {
        repository.save(entity);
    }
}