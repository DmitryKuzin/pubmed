package com.liyametrics.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Record {

    @Id
    private String PMID;

    private String url;

    private String doi;

    private String citation;

    public Record(){}

    public Record(String PMID, String url, String doi, String citation) {
        this.PMID = PMID;
        this.url = url;
        this.doi = doi;
        this.citation = citation;
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
}
