package edu.alvin.core.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class RepositorySupport<T> implements Repository<T> {
    private Class<T> entityType;

    @PersistenceContext
    private EntityManager em;

    public RepositorySupport() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        entityType = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public EntityManager getEm() {
        return em;
    }

    @Override
    public Optional<T> find(Object primaryKey) {
        T entity = em.find(entityType, primaryKey);
        return entity == null ? Optional.empty() : Optional.of(entity);
    }

    protected Optional<T> findBy(String ql, QueryPrepare prepare) {
        TypedQuery<T> query = em.createQuery(ql, entityType);
        if (prepare != null) {
            prepare.prepare(query);
        }
        List<T> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    protected List<T> list(String ql, QueryPrepare queryPrepare) {
        TypedQuery<T> query = em.createQuery(ql, entityType);
        if (queryPrepare != null) {
            queryPrepare.prepare(query);
        }
        return query.getResultList();
    }

    protected long count(String ql, QueryPrepare queryPrepare) {
        Query query = em.createQuery(ql);
        if (queryPrepare != null) {
            queryPrepare.prepare(query);
        }
        return (long) query.getSingleResult();
    }

    protected int execute(String ql, QueryPrepare prepare) {
        Query query = em.createQuery(ql);
        if (prepare != null) {
            prepare.prepare(query);
        }
        return query.executeUpdate();
    }

    @Override
    public void save(T entity) {
        em.persist(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }
}
