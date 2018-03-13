package com.liyametrics.TO.altmetric;

import java.io.Serializable;

public class Context implements Serializable {

    private Rate all;

    public Rate getAll() {
        return all;
    }

    public void setAll(Rate all) {
        this.all = all;
    }
}
