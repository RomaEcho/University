package com.foxmindedjavaspring.university.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractGenericDao<T> implements GenericDao<T> {

    private final Class<T> persistenceClass;
    @PersistenceContext
    private EntityManager entityManager;

    protected AbstractGenericDao(Class<T> persistenceClass) {
        this.persistenceClass = persistenceClass;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public T findById(Long id) {
        return getEntityManager().find(persistenceClass, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
            return entityManager.createQuery(
                        "Select t from "
                         + persistenceClass.getSimpleName()
                         + " t")
                        .getResultList();
    }

    @Override
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void delete(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Override
    public T update(T entity) {
        return getEntityManager().merge(entity);
    }
}