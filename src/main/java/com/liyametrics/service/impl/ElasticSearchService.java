package com.liyametrics.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyametrics.domain.elasticsearch.Article;
import com.liyametrics.domain.elasticsearch.ShortArticle;
import com.liyametrics.domain.elasticsearch.searchresult.Hits;
import com.liyametrics.domain.elasticsearch.searchresult.Result;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class ElasticSearchService {


//    private TransportClient client;

    private final String SEARCH_ENDPOINT = "/articles/article/_search?pretty";
    private final String FILTER_CAT_ENDPOINT = "/shortarticles/shortarticle/_search?q=categories";
    private final String FILTER_AUT_ENDPOINT = "/shortarticles/shortarticle/_search?q=authors";
    private final String INPUT_ENDPOINT = "/articles/article/";
    private final String FILTER_INDEX_INPUT_ENDPOINT = "/shortarticles/shortarticle/";
    private final String ELASTIC_HOST = "bfa0b04f654c40ec5ffc0bb4d3154bb7.us-east-1.aws.found.io";

    private ObjectMapper mapper;

    private RestClient restClient;

    public ElasticSearchService(){

        mapper = new ObjectMapper();

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "ou7lPqPpYpQ6xx0cLNJpIi55"));


        restClient = RestClient.builder(new HttpHost(ELASTIC_HOST, 9243, "https"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                }).build();


    }

    public void writeToElastic(Article article) {
        String json = toJSON(article);

        if(json != null) {
            HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);
            try {
                restClient.performRequest(
                        "PUT",
                        INPUT_ENDPOINT + article.getPMID(),
                        Collections.<String, String>emptyMap(),
                        entity);

                writeToElastic(new ShortArticle(
                        article.getPMID(),
                        article.getTitle(),
                        article.getShortText(),
                        article.getCategories(),
                        article.getRank(),
                        article.getAuthors()
                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void writeToElastic(ShortArticle article) throws IOException {
        String json = toJSON(article);

        if(json != null) {
            HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);
            restClient.performRequest(
                        "PUT",
                        FILTER_INDEX_INPUT_ENDPOINT + article.getPMID(),
                        Collections.<String, String>emptyMap(),
                        entity);

        }

    }

    public List<ShortArticle> filterByCategory(String category, Integer pageNum, Integer limit) {

        String jsonQuery = "{" +
                "\"_source\": [\"PMID\",\"title\",\"shortText\",\"categories\",\"rank\",\"authors\"]," +
                " \"from\":"+ limit * (pageNum-1) +" ,\n" +
                " \"size\": " + limit + "," +
                "\"sort\": [{\"rank\": \"desc\"}]" +
                "}";

        return executeQuery("GET", FILTER_CAT_ENDPOINT + encodeFilterUrl(category), jsonQuery);

    }

    public List<ShortArticle> search(String searchQuery, Integer pageNum, Integer limit) {

        String jsonQuery = "{" +
                "\"_source\": [\"PMID\",\"title\",\"shortText\",\"categories\",\"rank\",\"authors\"]," +
                " \"from\":"+ limit * (pageNum-1) +" ,\n" +
                " \"size\": " + limit + "," +
                "  \"query\": {" +
                "    \"simple_query_string\": {" +
                "      \"query\": \""+ searchQuery+ "\"," +
                "      \"fields\": [" +
                "        \"title^3\"," +
                "        \"shortText^2\"," +
                "        \"fullText\"" +
                "      ]" +
                "    }" +
                "  }," +
                "\"sort\": [{\"rank\": \"desc\"}]" +
                "}";

        return executeQuery("POST", SEARCH_ENDPOINT, jsonQuery);
    }

    public List<ShortArticle> filterByAuthor(String author, Integer pageNum, Integer limit) {
        String jsonQuery = "{" +
                "\"_source\": [\"PMID\",\"title\",\"shortText\",\"categories\",\"rank\",\"authors\"]," +
                " \"from\":"+ limit * (pageNum-1) +" ,\n" +
                " \"size\": " + limit + "," +
                "\"sort\": [{\"rank\": \"desc\"}]" +
                "}";

        return executeQuery("GET", FILTER_AUT_ENDPOINT + encodeFilterUrl(author), jsonQuery);

    }

    private List<ShortArticle> executeQuery(String method, String endpoint, String jsonQuery) {
        HttpEntity entity = new NStringEntity(jsonQuery, ContentType.APPLICATION_JSON);
        //TODO logging
        Response indexResponse = null;
        Result result = null;
        try {
            indexResponse = restClient.performRequest(
                    method,
                    endpoint,
                    Collections.<String, String>emptyMap(),
                    entity);
            String output = EntityUtils.toString(indexResponse.getEntity());
            System.out.println(output);


            result = mapper.readValue(output, Result.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<ShortArticle> list = new ArrayList<>();
        Hits[] hits = result.getHits().getHits();

        //convert result to List of ShortArticles
        if(hits.length>0) {
            for (int i = 0; i < hits.length; i++) {

                ShortArticle source = hits[i].get_source();

                if(source!=null) list.add(source);
            }
        }

        return list;
    }

    private String toJSON(Object obj) {
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    private String encodeFilterUrl(String category) {
        return URLEncoder.encode(":\"" + category + "\"");
    }
}
