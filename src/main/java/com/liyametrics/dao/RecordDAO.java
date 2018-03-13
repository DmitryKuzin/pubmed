package com.liyametrics.dao;

import com.liyametrics.domain.Record;
import com.liyametrics.utils.Period;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface RecordDAO {

    Serializable save(Record record);

    Record findById(final Serializable id);

    List<Record> getTopRecordsByPeriod(Period period, Integer limit);
}
