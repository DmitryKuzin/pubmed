package com.liyametrics.TO.altmetric;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Rate implements Serializable {

    private Long count;
    private Double mean;
    private Long rank;
    private Long pctl;
    @JsonProperty("higher_than")
    private Long higherThan;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getPctl() {
        return pctl;
    }

    public void setPctl(Long pctl) {
        this.pctl = pctl;
    }

    public Long getHigherThan() {
        return higherThan;
    }

    public void setHigherThan(Long higherThan) {
        this.higherThan = higherThan;
    }
}
