package com.liyametrics.service.impl;

import com.liyametrics.dao.RecordDAO;
import com.liyametrics.domain.Record;
import com.liyametrics.service.RecordService;
import com.liyametrics.utils.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class RecordServiceImpl implements RecordService{

    @Autowired
    RecordDAO recordDAO;


    @Override
    public Record getRecord(String id) {
        return recordDAO.findById(id);
    }

    @Override
    public List<Record> getTopRecordsByPeriod(Period period, Integer limit) {

        List<Record> topRecordsByPeriod = recordDAO.getTopRecordsByPeriod(period, limit);

        if(topRecordsByPeriod == null || topRecordsByPeriod.size() == 0) {
            //fetch data for this period
        }

        return topRecordsByPeriod;
    }
}
