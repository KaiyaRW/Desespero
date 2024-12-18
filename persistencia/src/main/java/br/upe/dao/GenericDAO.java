package br.upe.dao;

import java.util.List;

import jakarta.persistence.EntityManager;

public abstract class GenericDAO<T> {

    private Class<T> persistentClass;
    protected EntityManager entityManager; // Agora inicializado via construtor

    public GenericDAO(Class<T> persistentClass, EntityManager entityManager) {
        this.persistentClass = persistentClass;
        this.entityManager = entityManager; // Recebe a instância de EntityManager
    }

    public T findById(Long id) {
        return entityManager.find(persistentClass, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + persistentClass.getName(), persistentClass).getResultList();
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