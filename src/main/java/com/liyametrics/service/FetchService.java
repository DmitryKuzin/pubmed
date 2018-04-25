package com.liyametrics.service;

import com.liyametrics.domain.Article;
import com.liyametrics.utils.Period;

import java.util.List;

public interface FetchService {

    void fetch(List<String> range);

    void fetch(Period period);
}
