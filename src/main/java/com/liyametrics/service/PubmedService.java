package com.liyametrics.service;

import com.liyametrics.utils.Period;

import java.util.Date;
import java.util.List;

public interface PubmedService {

    void fetchRecords(List<Date> range);

    void fetchRecords(Period period);

    String convertToDoi(String pmid);
}
