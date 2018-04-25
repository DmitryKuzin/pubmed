package com.liyametrics.service.impl;

import com.liyametrics.TO.altmetric.Altmetrica;
import com.liyametrics.dao.ArticleDAO;
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
    public AltmetricServiceImpl(RestTemplate restTemplate, ArticleDAO recordDAO) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Altmetrica getRate(String doi) {

        String queryString = ALTMETRICA_URL + doi;
        try {
            ResponseEntity<Altmetrica> responseEntity =
                    restTemplate.getForEntity(queryString, Altmetrica.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
        } catch (Throwable t) {
            System.out.println(t.toString());
        }

        return null;

    }
}
