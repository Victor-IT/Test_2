package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.model.Record;
import com.vitkulov.tests.Test_2.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.vitkulov.tests.Test_2.service.RecordSpecifications.getRecordsByUserId;

@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Record> findByIdCriteria(Long id) {
        Specification<Record> specification = getRecordsByUserId(id);
        return recordRepository.findAll(specification);
    }


}
