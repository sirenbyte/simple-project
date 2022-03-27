package com.example.demo.service.base;

import com.example.demo.repository.base.BaseRepository;
import com.example.demo.util.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T, ID extends Serializable, R extends BaseRepository<T, ID>> implements BaseService<T, ID> {
    private R repository;

    protected R getRepository() {
        return this.repository;
    }

    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }
    @Transactional
    public T save(T value) {
        return this.getRepository().saveAndFlush(value);
    }

    @Transactional
    public T edit(T value) {
        return this.getRepository().saveAndFlush(value);
    }

    @Transactional
    public Optional<T> find(ID id) {
        return this.getRepository().findById(id);
    }

    @Override
    public T findOrThrowNotFound(ID id) {
        return this.getRepository().findById(id).orElseThrow(() -> notFoundException(id));
    }

    @Override
    public RuntimeException notFoundException(ID id) {
        return new EntityNotFoundException(this.getClass(), id);
    }


    @Transactional
    public void delete(ID id) {
        this.getRepository().deleteById(id);
    }

    @Transactional
    public void delete(T id) {
        this.getRepository().delete(id);
    }

    @Transactional
    public List<T> getAll() {
        return this.getRepository().findAll();
    }

}
