package com.liyametrics.service;

import com.liyametrics.domain.Article;
import com.liyametrics.utils.Period;

import java.util.Date;
import java.util.List;

public interface PubmedService {

    List<Article> fetchRecords(List<String> range);

    List<Article> fetchRecords(Period period);

    String convertToDoi(String pmid);
}
