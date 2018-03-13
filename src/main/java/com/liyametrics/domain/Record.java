package com.liyametrics.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.util.Date;

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name    =   "getRecordsOrderedByDate",
                query   =   "SELECT *  FROM record WHERE date BETWEEN ? AND ? ORDER BY rank DESC LIMIT ?;",
                resultClass=Record.class
        )
})
public class Record {

    @Id
    private String PMID;

    private String url;

    private String doi;

    private String citation;

    private Long rank;

    private Date date;

    public Record(){}

    public Record(String PMID, String url, String doi, String citation, Long rank, Date date) {
        this.PMID = PMID;
        this.url = url;
        this.doi = doi;
        this.citation = citation;
        this.rank = rank;
        this.date = date;
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
}
