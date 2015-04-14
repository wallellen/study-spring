package edu.alvin.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class Builder<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    public abstract T build();
    public abstract T create();

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
