package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.dto.FilterFormDto;
import com.vitkulov.tests.Test_2.model.Record;

import java.util.List;

public interface RecordService {

    List<Record> findRecordsByCriteria(Long id, FilterFormDto filterFormDto);
}
