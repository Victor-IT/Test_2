package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.model.Record;
import com.vitkulov.tests.Test_2.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Record> findByIdCriteria(String id) {

        Specification<Record> specification = new Specification<Record>() {
            @Override
            public Predicate toPredicate(Root<Record> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("uplink")), "512");
            }
        };

        return recordRepository.findAll(specification);
    }
}
