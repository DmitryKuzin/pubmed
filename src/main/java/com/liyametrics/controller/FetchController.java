package com.liyametrics.controller;

import com.liyametrics.TO.*;
import com.liyametrics.service.PubmedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


@RestController
public class FetchController {

    private PubmedService pubmedService;

    @Autowired
    public FetchController(PubmedService pubmedService) {
        this.pubmedService = pubmedService;
    }

    @RequestMapping("/fetchData")
    public String fetchData() throws JAXBException {

        String fetchedMessage = "Fetched " + pubmedService.fetchRecords().toString() + " records from PubMed";

        return fetchedMessage;
    }
}
