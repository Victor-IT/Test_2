package com.vitkulov.tests.Test_2.repository;

import com.vitkulov.tests.Test_2.model.Record;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface RecordRepository extends Repository<Record, Long>, JpaSpecificationExecutor<Record> {
}
