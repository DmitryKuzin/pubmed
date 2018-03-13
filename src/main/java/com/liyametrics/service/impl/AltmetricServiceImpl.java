package com.liyametrics.service.impl;

import com.liyametrics.TO.altmetric.Altmetrica;
import com.liyametrics.dao.RecordDAO;
import com.liyametrics.service.AltmetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AltmetricServiceImpl implements AltmetricService {

    private final RestTemplate restTemplate;
    private final String ALTMETRICA_URL = "https://api.altmetric.com/v1/doi/";

    @Autowired
    public AltmetricServiceImpl(RestTemplate restTemplate, RecordDAO recordDAO) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Long getRate(String doi) {
        Long rank = null;

        String queryString = ALTMETRICA_URL + doi;
        try {
            ResponseEntity<Altmetrica> responseEntity =
                    restTemplate.getForEntity(queryString, Altmetrica.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                rank = responseEntity.getBody().getContext().getAll().getRank();
            }
        } catch (Throwable t) {
            System.out.println(t.toString());
        }

        return rank != null ? rank : 0L;

    }
}
