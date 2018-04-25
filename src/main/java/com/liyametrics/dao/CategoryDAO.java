package com.liyametrics.dao;


import com.liyametrics.domain.Category;

import java.io.Serializable;

public interface CategoryDAO {
    Serializable save(Category categories);

    Category findById(final Serializable id);
}
