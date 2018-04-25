package com.liyametrics.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    private String PMID;

    private String[] types;

    public Category() {
    }

    public Category(String PMID, String[] types) {
        this.PMID = PMID;
        this.types = types;
    }

    public String getPMID() {
        return PMID;
    }

    public void setPMID(String PMID) {
        this.PMID = PMID;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
