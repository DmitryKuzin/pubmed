package com.liyametrics.controller;

import com.liyametrics.domain.elasticsearch.ShortArticle;
import com.liyametrics.service.impl.ElasticSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "Search API", description = "Поиск по статьям, их авторам и категориям")
public class SearchController {

    public ElasticSearchService elasticSearchService;

    public SearchController(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @ApiOperation(value = "search")
    @GetMapping("/search")
    public List<ShortArticle> search(String searchQuery, Integer limit, Integer pageNum) {

        return elasticSearchService.search(searchQuery, pageNum, limit);

    }

    @ApiOperation(value = "filter Articles by category")
    @GetMapping("/categoryFilter")
    public List<ShortArticle> filterCategory(String category, Integer limit, Integer pageNum) {

        return elasticSearchService.filterByCategory(category, pageNum, limit);

    }

    @ApiOperation(value = "filter Articles by author")
    @GetMapping("/authorFilter")
    public List<ShortArticle> filterAuthor(String author, Integer limit, Integer pageNum) {

        return elasticSearchService.filterByAuthor(author, pageNum, limit);

    }

}
