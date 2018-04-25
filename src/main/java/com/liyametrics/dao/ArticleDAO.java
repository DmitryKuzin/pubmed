package com.liyametrics.dao;

import com.liyametrics.domain.Article;
import com.liyametrics.utils.Period;

import java.io.Serializable;
import java.util.List;

public interface ArticleDAO {

    Serializable save(Article article);

    Article findById(final Serializable id);

    List<Article> getTopRecordsByPeriod(Period period, Integer limit);
}
