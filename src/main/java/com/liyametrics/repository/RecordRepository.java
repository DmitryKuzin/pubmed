package com.liyametrics.repository;

import com.liyametrics.domain.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record, String> {
}
