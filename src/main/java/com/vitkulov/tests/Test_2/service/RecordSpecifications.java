package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.model.Record;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

final class RecordSpecifications {

    private RecordSpecifications() {
    }

    static Specification<Record> getRecordsByUserId(Long id) {
        return new Specification<Record>() {
            @Override
            public Predicate toPredicate(Root<Record> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("user"), id);
            }
        };
    }
}
