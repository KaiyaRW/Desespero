package br.upe.dao;

import jakarta.persistence.EntityManager;
import java.util.List;

public class GenericDAO<T> {
    private final Class<T> entityType;
    protected final EntityManager entityManager;

    public GenericDAO(Class<T> entityType, EntityManager entityManager) {
        this.entityType = entityType;
        this.entityManager = entityManager;
    }

    public void save(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void update(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(T entity) {
        try {
            entityManager.getTransaction().begin();
            T mergedEntity = entityManager.merge(entity);
            entityManager.remove(mergedEntity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public T findById(Long id) {
        return entityManager.find(entityType, id);
    }

    public List<T> findAll() {
        try {
            return entityManager.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e", entityType)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todas as entidades do tipo: " + entityType.getSimpleName(), e);
        }
    }
}