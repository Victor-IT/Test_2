package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.dto.FilterFormDto;
import com.vitkulov.tests.Test_2.model.Record;
import com.vitkulov.tests.Test_2.repository.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.vitkulov.tests.Test_2.service.RecordSpecifications.*;
import static org.springframework.data.jpa.domain.Specifications.where;

@Slf4j
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
        LocalDateTime start = null;
        LocalDateTime end = null;
        try {
            start = LocalDateTime.parse(filterFormDto.getStartDate());
        } catch (Exception e) {
            log.info("Wrong or empty StartDate formField");
        }
        try {
            end = LocalDateTime.parse(filterFormDto.getEndDate());
        } catch (Exception e) {
            log.info("Wrong or empty EndDate formField");
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
