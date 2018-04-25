package com.liyametrics.service;

import com.liyametrics.TO.altmetric.Altmetrica;

import java.util.Map;

public interface AltmetricService {

    Altmetrica getRate(String doi);
}
