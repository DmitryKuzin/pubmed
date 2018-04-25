package com.liyametrics.domain.elasticsearch.searchresult;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonAutoDetect
public class Result implements Serializable {

    @JsonProperty("took")
    private String took;

    @JsonProperty("timed_out")
    private String timed_out;

    @JsonProperty("_shards")
    private Shards shards;

    @JsonProperty("hits")
    private OuterHits hits;

    public Result() {
    }

    public Result(String took, String timed_out, Shards shards, OuterHits hits) {
        this.took = took;
        this.timed_out = timed_out;
        this.shards = shards;
        this.hits = hits;
    }

    public String getTook() {
        return took;
    }

    public void setTook(String took) {
        this.took = took;
    }

    public String getTimed_out() {
        return timed_out;
    }

    public void setTimed_out(String timed_out) {
        this.timed_out = timed_out;
    }

    public OuterHits getHits() {
        return hits;
    }

    public void setHits(OuterHits hits) {
        this.hits = hits;
    }

    public Shards getShards() {
        return shards;
    }

    public void setShards(Shards shards) {
        this.shards = shards;
    }
}
