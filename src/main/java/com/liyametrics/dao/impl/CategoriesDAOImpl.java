package com.liyametrics.dao.impl;

import com.liyametrics.dao.CategoryDAO;
import com.liyametrics.domain.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Repository
@Transactional
public class CategoriesDAOImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(Category categories) {
        return getSession().save(categories);
    }

    @Override
    public Category findById(final Serializable id) {
        return getSession().get(Category.class, id);
    }
}
