package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.model.Record;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

final class RecordSpecifications {

    private RecordSpecifications() {
    }

    static Specification<Record> hasUserId(Long id) {
        return new Specification<Record>() {
            @Override
            public Predicate toPredicate(Root<Record> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("user"), id);
            }
        };
    }

    static Specification<Record> dateFrom(LocalDate from) {
        return new Specification<Record>() {
            @Override
            public Predicate toPredicate(Root<Record> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("date"), from);
            }
        };
    }

    static Specification<Record> dateTo(LocalDate to) {
        return new Specification<Record>() {
            @Override
            public Predicate toPredicate(Root<Record> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("date"), to);
            }
        };
    }

    static Specification<Record> dateFromTo(LocalDate from, LocalDate to) {
        return new Specification<Record>() {
            @Override
            public Predicate toPredicate(Root<Record> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("date"), from),
                        criteriaBuilder.lessThanOrEqualTo(root.get("date"), to)
                );

            }
        };
    }

    static Specification<Record> idFromTo(Long from, Long to) {
        return new Specification<Record>() {
            @Override
            public Predicate toPredicate(Root<Record> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("id"), from),
                        criteriaBuilder.lessThanOrEqualTo(root.get("id"), to)
                );
            }
        };
    }
}
