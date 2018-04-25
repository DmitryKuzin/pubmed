package com.liyametrics.domain.elasticsearch;

import java.io.Serializable;

public class Article implements Serializable {

    private String PMID;

    private String doi;

    private String title;

    private String shortText;

    private String fullText;

    private String url;

    private Long rank;

    private String date;

    private String[] categories;

    private String[] authors;

    private String citation;

    public Article() {
    }

    public Article(String PMID, String doi, String title, String shortText, String fullText, String url, Long rank, String date, String[] categories, String[] authors, String citation) {
        this.PMID = PMID;
        this.doi = doi;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.url = url;
        this.rank = rank;
        this.date = date;
        this.categories = categories;
        this.authors = authors;
        this.citation = citation;
    }

    public String getPMID() {
        return PMID;
    }

    public void setPMID(String PMID) {
        this.PMID = PMID;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
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

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }
}
