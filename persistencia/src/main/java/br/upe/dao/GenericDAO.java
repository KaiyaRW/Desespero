package br.upe.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDAO<T> {

    private Class<T> persistentClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public GenericDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public T findById(Long id) {
        return entityManager.find(persistentClass, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + persistentClass.getName()).getResultList();
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }
}