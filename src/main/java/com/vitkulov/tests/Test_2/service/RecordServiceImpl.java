package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.model.Record;
import com.vitkulov.tests.Test_2.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.vitkulov.tests.Test_2.service.RecordSpecifications.dateFromTo;
import static com.vitkulov.tests.Test_2.service.RecordSpecifications.hasUserId;

@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Record> findRecordsByCriteria(Long id) {

        // fixme: тест диапазона
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start = LocalDate.parse("29-01-2018", formatter);
        LocalDate end = LocalDate.parse("02-02-2018", formatter);

        Specification<Record> specId = hasUserId(id);
        Specification<Record> specRange = dateFromTo(start, end);
        return recordRepository.findAll(Specifications.where(specId).and(specRange));
    }


}
