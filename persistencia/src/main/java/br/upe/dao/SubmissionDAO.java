package br.upe.dao;

import br.upe.pojos.Submission;

import jakarta.persistence.EntityManager;

public class SubmissionDAO extends GenericDAO<Submission> {

    public SubmissionDAO(EntityManager entityManager) {
        super(Submission.class, entityManager);
    }

}