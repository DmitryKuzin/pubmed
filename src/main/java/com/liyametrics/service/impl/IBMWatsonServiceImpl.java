package com.liyametrics.service.impl;


import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;
import com.liyametrics.service.IBMWatsonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBMWatsonServiceImpl implements IBMWatsonService {

    public List<CategoriesResult> miningTextByWatson(String text) {
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                "8265edb8-ff19-48fd-af22-7e9a393de079",
                "rCfosrv61ucL"
        );

        EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
                .emotion(true)
                .sentiment(true)
                .limit(2)
                .build();

        CategoriesOptions categories = new CategoriesOptions();


        KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
                .emotion(true)
                .sentiment(true)
                .limit(2)
                .build();

        Features features = new Features.Builder()
                .entities(entitiesOptions)
                .keywords(keywordsOptions)
                .categories(categories)
                .build();


        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(text)
                .features(features)
                .build();

        AnalysisResults response = service
                .analyze(parameters)
                .execute();

        return response.getCategories();
    }
}
