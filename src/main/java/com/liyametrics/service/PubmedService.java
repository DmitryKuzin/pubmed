package com.liyametrics.service;

import com.liyametrics.utils.Period;

import java.util.Date;
import java.util.List;

public interface PubmedService {

    void fetchRecords(List<String> range);

    List<String> fetchRecords(Period period);

    String convertToDoi(String pmid);
}
