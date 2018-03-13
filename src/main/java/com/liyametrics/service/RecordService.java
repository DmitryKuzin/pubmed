package com.liyametrics.service;

import com.liyametrics.domain.Record;
import com.liyametrics.utils.Period;

import java.util.List;

public interface RecordService {

    Record getRecord(String id);

    List<Record> getTopRecordsByPeriod(Period period, Integer limit);

}
