package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.model.Record;

import java.util.List;

public interface RecordService {

    List<Record> findByIdCriteria(Long id);
}
