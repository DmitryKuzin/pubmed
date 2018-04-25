package com.liyametrics.service.impl;

import com.liyametrics.TO.altmetric.Altmetrica;
import com.liyametrics.TO.fetch.*;
import com.liyametrics.TO.doi.*;
import com.liyametrics.TO.fetch.RecordType;
import com.liyametrics.dao.ArticleDAO;
import com.liyametrics.domain.Article;
import com.liyametrics.service.AltmetricService;
import com.liyametrics.service.PubmedService;
import com.liyametrics.utils.DateTimeUtil;
import com.liyametrics.utils.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PubmedServiceImpl implements PubmedService {

    private final String PUBMED_URL = "https://www.ncbi.nlm.nih.gov/pmc/utils/oa/oa.fcgi?from=";
    private final String PUBMED_TO_DOI_URL = "https://www.ncbi.nlm.nih.gov/pmc/utils/idconv/v1.0/?tool=my_tool&email=my_email@example.com&versions=no&ids=";
    private final RestTemplate restTemplate;
    private final ArticleDAO recordDAO;
    private final AltmetricService altmetricService;


    @Autowired
    public PubmedServiceImpl(RestTemplate restTemplate, ArticleDAO recordDAO, AltmetricService altmetricService) {
        this.restTemplate = restTemplate;
        this.recordDAO = recordDAO;
        this.altmetricService = altmetricService;
    }


    @Override
    public List<Article> fetchRecords(List<String> range) {
        List<Article> articles = new ArrayList<>();

        range.forEach(r -> {
            String queryString = PUBMED_URL + r;
            articles.addAll(processFetchingData(queryString, DateTimeUtil.fromString(r)));
        });

        return articles;
    }

    @Override
    public List<Article> fetchRecords(Period period) {

        String queryString = PUBMED_URL + DateTimeUtil.getDate(period);
        return processFetchingData(queryString, DateTimeUtil.getDateDate(period));
    }

    private List<Article> processFetchingData(String url, Date forDay) {
        List<Article> pmids = new ArrayList<>();

        try {
            ResponseEntity<OA> responseEntity =
                    restTemplate.getForEntity(url, OA.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                Stream<RecordType> stream = responseEntity.getBody().getRecords().getRecord().stream();

                stream.forEach(r -> {

                    String doi = convertToDoi(r.getId());

                    if(recordDAO.findById(r.getId()) == null) {

                        Altmetrica rate = altmetricService.getRate(doi);

                        if(rate!=null) {
                            Article article = new Article(
                                    r.getId(),
                                    r.getLink().getHref(),
                                    doi,
                                    r.getCitation(),
                                    rate.getContext().getAll().getRank(),
                                    forDay,
                                    rate.getAuthors(),
                                    rate.getTitle()
                            );
                            pmids.add(article);
                        }
                    }
                });
            }
            else {
                System.out.println("Oops something went wrong here : PubmedServiceImpl:100");
            }

        } catch (Throwable t) {
            System.out.println(t.toString());
        }

        return pmids;
    }

    @Override
    public String convertToDoi(String pmid) {

        String result = "";

        String queryString = PUBMED_TO_DOI_URL + pmid;
        try {
            ResponseEntity<PMCIDSType> responseEntity =
                    restTemplate.getForEntity(queryString, PMCIDSType.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                PMCIDSType body = responseEntity.getBody();
                result = body.getRecord().getDoi();
            }
        } catch (Throwable t) {
            System.out.println(t.toString());
        }

        return result;
    }


}
