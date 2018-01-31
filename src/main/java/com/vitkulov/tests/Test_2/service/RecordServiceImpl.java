package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.dto.FilterFormDto;
import com.vitkulov.tests.Test_2.model.Record;
import com.vitkulov.tests.Test_2.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.vitkulov.tests.Test_2.service.RecordSpecifications.*;
import static org.springframework.data.jpa.domain.Specifications.where;

@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Record> findRecordsByCriteria(Long id, FilterFormDto filterFormDto) {

        Specification<Record> specification = hasUserId(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start = null;
        LocalDate end = null;
        try {
            start = LocalDate.parse(filterFormDto.getStartDate(), formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            end = LocalDate.parse(filterFormDto.getEndDate(), formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (start != null) {
            specification = where(specification).and(dateFrom(start));
        }

        if (end != null) {
            specification = where(specification).and(dateTo(end));
        }

        return recordRepository.findAll(specification);
    }


}
