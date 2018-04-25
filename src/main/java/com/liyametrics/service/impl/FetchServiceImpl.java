package com.liyametrics.service.impl;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesResult;
import com.liyametrics.dao.ArticleDAO;
import com.liyametrics.dao.CategoryDAO;
import com.liyametrics.domain.Article;
import com.liyametrics.domain.Category;
import com.liyametrics.service.FetchService;
import com.liyametrics.service.IBMWatsonService;
import com.liyametrics.service.PubmedService;
import com.liyametrics.utils.DateTimeUtil;
import com.liyametrics.utils.InfoMessageDecorator;
import com.liyametrics.utils.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FetchServiceImpl implements FetchService {

    private PubmedService pubmedService;
    private PdfArticleTextExtractorService pdfArticleTextExtractorService;
    private IBMWatsonService ibmWatsonService;
    private CategoryDAO categoryDAO;
    private ElasticSearchService elasticSearchService;
    private ArticleDAO articleDAO;

    @Autowired
    public FetchServiceImpl(PubmedService pubmedService, PdfArticleTextExtractorService pdfArticleTextExtractorService,
                            IBMWatsonService ibmWatsonService, CategoryDAO categoryDAO, ElasticSearchService elasticSearchService,
                            ArticleDAO articleDAO) {
        this.pubmedService = pubmedService;
        this.pdfArticleTextExtractorService = pdfArticleTextExtractorService;
        this.ibmWatsonService = ibmWatsonService;
        this.categoryDAO = categoryDAO;
        this.elasticSearchService = elasticSearchService;
        this.articleDAO = articleDAO;

    }


    @Override
    public void fetch(List<String> range) {

        processfetching(pubmedService.fetchRecords(range));
    }

    @Override
    public void fetch(Period period) {

        processfetching(pubmedService.fetchRecords(period));
    }

    private void processfetching(List<Article> articles){

        InfoMessageDecorator.print("Получили статьи с Pubmed API");

        articles.forEach(article -> {

            String text = pdfArticleTextExtractorService.extractText(article.getPMID(),article.getUrl());

            InfoMessageDecorator.print("Вытащили текст из pdf документа статьи");

            List<CategoriesResult> categoriesResults = ibmWatsonService.miningTextByWatson(text);

            InfoMessageDecorator.print("Получили категории для статьи из IBM Watson API");

            Category categories = new Category();

            categories.setPMID(article.getPMID());

            String[] array = new String[categoriesResults.size()];

            categoriesResults.stream().map(CategoriesResult::getLabel).collect(Collectors.toList()).toArray(array);

            categories.setTypes(array);

            if(categoryDAO.findById(article.getPMID()) == null) {
                categoryDAO.save(categories);
            }

            String preview;

            try{
                preview = text.substring(0, 300).concat("...");
            } catch (StringIndexOutOfBoundsException e) {
                preview = "";
            }



            elasticSearchService.writeToElastic(new com.liyametrics.domain.elasticsearch.Article(
                    article.getPMID(),
                    article.getDoi(),
                    article.getTitle(),
                    preview,
                    text,
                    article.getUrl(),
                    article.getRank(),
                    DateTimeUtil.getString(article.getDate()),
                    array,
                    article.getAuthors(),
                    article.getCitation())
            );

            articleDAO.save(article);

            InfoMessageDecorator.print("Сохранили стаью в elastic и базу данных. \n"+article.toString());


        });
    }
}
