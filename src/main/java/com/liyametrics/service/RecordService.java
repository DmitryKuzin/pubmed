package com.liyametrics.service;

import com.liyametrics.domain.Article;
import com.liyametrics.utils.Period;

import java.util.List;

public interface RecordService {

    Article getRecord(String id);

    List<Article> getTopRecordsByPeriod(Period period, Integer limit, Integer pageNum);

}
