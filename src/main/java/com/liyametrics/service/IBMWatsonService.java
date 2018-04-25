package com.liyametrics.service;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesResult;

import java.util.List;

public interface IBMWatsonService {

    List<CategoriesResult> miningTextByWatson(String text);
}
