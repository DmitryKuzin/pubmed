package com.liyametrics.dao.impl;

import com.liyametrics.dao.ArticleDAO;
import com.liyametrics.domain.Article;
import com.liyametrics.utils.DateTimeUtil;
import com.liyametrics.utils.Period;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;


@Repository
@Transactional
public class ArticleDAOImpl implements ArticleDAO {


    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(Article record) {
        return getSession().save(record);
    }

    @Override
    public Article findById(final Serializable id) {
        return getSession().get(Article.class, id);
    }

    @Override
    public List<Article> getTopRecordsByPeriod(Period period, Integer limit, Integer pageNum) {

        Session session = getSession().getSessionFactory().openSession();

        Query query = session.createQuery("FROM Article r WHERE r.date BETWEEN ? AND ? ORDER BY r.rank DESC OFFSET ?");

        query.setMaxResults(limit);
        query.setParameter(0, DateTimeUtil.getDateDate(period));
        query.setParameter(1, DateTimeUtil.getDateDate(Period.TODAY));
        query.setParameter(3, limit * pageNum);

        System.out.println(DateTimeUtil.getDate(Period.TODAY));
        System.out.println(DateTimeUtil.getDate(period));

        List<Article> records = (List<Article>)query.list();

        session.close();

        return records;
    }

}

