package com.wora.Survey.It.common.Validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class ExistsValidator implements ConstraintValidator<Exists, Object> {

    private Class<?> entity;
    private String field;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(Exists constraintAnnotation) {
        this.entity = constraintAnnotation.entity();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String queryStr = "SELECT COUNT(e) FROM " + entity.getSimpleName() + " e WHERE e." + field + " = :value";
        Query query = entityManager.createQuery(queryStr);
        query.setParameter("value", value);

        Long count = (Long) query.getSingleResult();
        return count == 0;
    }
}

