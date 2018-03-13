package com.liyametrics.dao.impl;

import com.liyametrics.dao.RecordDAO;
import com.liyametrics.domain.Record;
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
public class RecordDAOImpl implements RecordDAO {


    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(Record record) {
        return getSession().save(record);
    }

    @Override
    public Record findById(final Serializable id) {
        return getSession().get(Record.class, id);
    }

    @Override
    public List<Record> getTopRecordsByPeriod(Period period, Integer limit) {

        Session session = getSession().getSessionFactory().openSession();

        Query query = session.createQuery("FROM Record r WHERE r.date BETWEEN ? AND ? ORDER BY r.rank DESC");

        query.setMaxResults(limit);
        query.setParameter(0, DateTimeUtil.getDateDate(period));
        query.setParameter(1, DateTimeUtil.getDateDate(Period.TODAY));

        System.out.println(DateTimeUtil.getDate(Period.TODAY));
        System.out.println(DateTimeUtil.getDate(period));

        List<Record> records = (List<Record>)query.list();

        session.close();

        return records;
    }

}

