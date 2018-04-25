package com.liyametrics.domain.elasticsearch;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonAutoDetect
public class ShortArticle implements Serializable {

    @JsonProperty("PMID")
    private String PMID;
    @JsonProperty("title")
    private String title;
    @JsonProperty("shortText")
    private String shortText;
    @JsonProperty("categories")
    private String[] categories;
    @JsonProperty("rank")
    private Long rank;
    @JsonProperty("authors")
    private String[] authors;

    public ShortArticle() {
    }

    public ShortArticle(String PMID, String title, String shortText, String[] categories, Long rank, String[] authors) {
        this.PMID = PMID;
        this.title = title;
        this.shortText = shortText;
        this.categories = categories;
        this.rank = rank;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPMID() {
        return PMID;
    }

    public void setPMID(String PMID) {
        this.PMID = PMID;
    }
}
