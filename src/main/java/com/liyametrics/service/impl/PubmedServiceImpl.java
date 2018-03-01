package com.liyametrics.service.impl;

import com.liyametrics.TO.OA;
import com.liyametrics.converters.OAConverter;
import com.liyametrics.domain.Record;
import com.liyametrics.repository.RecordRepository;
import com.liyametrics.service.PubmedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PubmedServiceImpl implements PubmedService {

    private final String pubmedUrl = "https://www.ncbi.nlm.nih.gov/pmc/utils/oa/oa.fcgi?from=2013-01-01";
    private final RestTemplate restTemplate;
    private final RecordRepository recordRepository;

    @Autowired
    public PubmedServiceImpl(RestTemplate restTemplate, RecordRepository recordRepository) {
        this.restTemplate = restTemplate;
        this.recordRepository = recordRepository;
    }


    @Override
    public Integer fetchRecords() {

        List<Record> res = new ArrayList<>();


        String queryString = pubmedUrl;
        try {
            ResponseEntity<OA> responseEntity =
                    restTemplate.getForEntity(queryString, OA.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                OA response = responseEntity.getBody();
                res.addAll(OAConverter.convertToDO(response));
            }
        } catch (Throwable t) {
            System.out.println(t.toString());
        }

        if(res.size()>0) {
            System.out.println("УРААААААААААА!");
            System.out.println("УРААААААААААА!");
            System.out.println("УРААААААААААА!");
            System.out.println(res.size());
        }

        recordRepository.save(res);
        return res.size();
    }
}
