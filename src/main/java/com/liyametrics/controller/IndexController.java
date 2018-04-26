package com.liyametrics.controller;

import com.liyametrics.domain.elasticsearch.ShortArticle;
import com.liyametrics.service.impl.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class IndexController {

    private ElasticSearchService elasticSearchService;

    @Autowired
    public IndexController(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @GetMapping(value = "/")
    public String mainPage() {


        return "index";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(String query, Integer limit, Integer pageNum, Model model) {

        List<ShortArticle> shortArticles = elasticSearchService.search(query, pageNum, limit);

        if(shortArticles.size()>11) {
            shortArticles = shortArticles.subList(0,10);
        }

        model.addAttribute("queryResult", shortArticles);

        return "index";

    }


    @RequestMapping(value = "filter", method = RequestMethod.GET)
    public String filter(String category, Model model) {
        List<ShortArticle> shortArticles = elasticSearchService.filterByCategory(category);
        if(shortArticles.size()>10) {
            shortArticles = shortArticles.subList(0,10);
        }

        model.addAttribute("queryResult", shortArticles);
        return "index";
    }

    @RequestMapping(value = "filterAuthor", method = RequestMethod.GET)
    public String filterAuthor(String author, Model model) {
        List<ShortArticle> shortArticles = elasticSearchService.filterByAuthor(author);
        if(shortArticles.size()>10) {
            shortArticles = shortArticles.subList(0,10);
        }

        model.addAttribute("queryResult", shortArticles);
        return "index";
    }
}
