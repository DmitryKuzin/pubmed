package com.liyametrics.domain.elasticsearch.searchresult;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.liyametrics.domain.elasticsearch.ShortArticle;

import java.io.Serializable;

@JsonAutoDetect
public class Hits implements Serializable {

    @JsonProperty("_index")
    private String _index;
    @JsonProperty("_type")
    private String _type;
    @JsonProperty("_id")
    private String _id;
    @JsonProperty("_score")
    private Double _score;
    @JsonProperty("_source")
    private ShortArticle _source;
    @JsonProperty("sort")
    private Integer[] sort;

    public Hits() {
    }

    public Hits(String _index, String _type, String _id, Double _score, ShortArticle _source, Integer[] sort) {
        this._index = _index;
        this._type = _type;
        this._id = _id;
        this._score = _score;
        this._source = _source;
        this.sort = sort;
    }

    public String get_index() {
        return _index;
    }

    public void set_index(String _index) {
        this._index = _index;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Double get_score() {
        return _score;
    }

    public void set_score(Double _score) {
        this._score = _score;
    }

    public ShortArticle get_source() {
        return _source;
    }

    public void set_source(ShortArticle _source) {
        this._source = _source;
    }

    public Integer[] getSort() {
        return sort;
    }

    public void setSort(Integer[] sort) {
        this.sort = sort;
    }
}
