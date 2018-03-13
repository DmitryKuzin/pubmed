package com.liyametrics.service.impl;

import com.liyametrics.TO.fetch.*;
import com.liyametrics.TO.doi.*;
import com.liyametrics.TO.fetch.RecordType;
import com.liyametrics.dao.RecordDAO;
import com.liyametrics.domain.Record;
import com.liyametrics.service.AltmetricService;
import com.liyametrics.service.PubmedService;
import com.liyametrics.utils.DateTimeUtil;
import com.liyametrics.utils.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PubmedServiceImpl implements PubmedService {

    private final String PUBMED_URL = "https://www.ncbi.nlm.nih.gov/pmc/utils/oa/oa.fcgi?from=";
    private final String PUBMED_TO_DOI_URL = "https://www.ncbi.nlm.nih.gov/pmc/utils/idconv/v1.0/?tool=my_tool&email=my_email@example.com&versions=no&ids=";
    private final RestTemplate restTemplate;
    private final RecordDAO recordDAO;
    private final AltmetricService altmetricService;


    @Autowired
    public PubmedServiceImpl(RestTemplate restTemplate, RecordDAO recordDAO, AltmetricService altmetricService) {
        this.restTemplate = restTemplate;
        this.recordDAO = recordDAO;
        this.altmetricService = altmetricService;
    }


    @Override
    public void fetchRecords(List<Date> range) {

    }

    @Override
    public void fetchRecords(Period period) {

        String queryString = PUBMED_URL + DateTimeUtil.getDate(period);
        try {
            ResponseEntity<OA> responseEntity =
                    restTemplate.getForEntity(queryString, OA.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                Stream<RecordType> stream = responseEntity.getBody().getRecords().getRecord().stream();

                stream.forEach(r -> {
                    String doi = convertToDoi(r.getId());

                    if(recordDAO.findById(r.getId()) == null) {
                        recordDAO.save(new Record(
                                r.getId(),
                                r.getLink().getHref(),
                                doi,
                                r.getCitation(),
                                altmetricService.getRate(doi),
                                DateTimeUtil.getDateDate(period)
                        ));
                    }else {
                        System.out.println("Id :" + r.getId() + " already exists in DB.");
                    }

                });
            }

        } catch (Throwable t) {
            System.out.println(t.toString());
        }
    }

    @Override
    public String convertToDoi(String pmid) {

        String result = "";

        String queryString = PUBMED_TO_DOI_URL + pmid;
        try {
            ResponseEntity<PMCIDSType> responseEntity =
                    restTemplate.getForEntity(queryString, PMCIDSType.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                PMCIDSType body = responseEntity.getBody();
                result = body.getRecord().getDoi();
            }
        } catch (Throwable t) {
            System.out.println(t.toString());
        }

        return result;
    }


}
