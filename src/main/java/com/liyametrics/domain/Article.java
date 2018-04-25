package com.liyametrics.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.util.Arrays;
import java.util.Date;

@Entity
public class Article {

    @Id
    private String PMID;

    private String url;

    private String doi;

    private String citation;

    private Long rank;

    private Date date;

    private String[] authors;

    private String title;

    public Article(){}

    public Article(String PMID, String url, String doi, String citation, Long rank, Date date, String[] authors, String title) {
        this.PMID = PMID;
        this.url = url;
        this.doi = doi;
        this.citation = citation;
        this.rank = rank;
        this.date = date;
        this.authors = authors;
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getPMID() {
        return PMID;
    }

    public void setPMID(String PMID) {
        this.PMID = PMID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Article{" +
                "PMID='" + PMID + '\'' +
                ", url='" + url + '\'' +
                ", doi='" + doi + '\'' +
                ", citation='" + citation + '\'' +
                ", rank=" + rank +
                ", date=" + date +
                ", authors=" + Arrays.toString(authors) +
                ", title='" + title + '\'' +
                '}';
    }
}
