package com.liyametrics.domain.elasticsearch.searchresult;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonAutoDetect
public class OuterHits implements Serializable {

    @JsonProperty("total")
    private String total;

    @JsonProperty("max_score")
    private Double max_score;

    @JsonProperty("hits")
    private Hits[] hits;

    public OuterHits() {
    }

    public OuterHits(String total, Double max_score, Hits[] hits) {
        this.total = total;
        this.max_score = max_score;
        this.hits = hits;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Double getMax_score() {
        return max_score;
    }

    public void setMax_score(Double max_score) {
        this.max_score = max_score;
    }

    public Hits[] getHits() {
        return hits;
    }

    public void setHits(Hits[] hits) {
        this.hits = hits;
    }
}
