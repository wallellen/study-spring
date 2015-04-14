package edu.alvin.core.jpa;

import java.util.Optional;

public interface Repository<T> {
    Optional<T> find(Object primaryKey);
    void save(T entity);
    void saveOrUpdate(T entity);
    void delete(T entity);
}
