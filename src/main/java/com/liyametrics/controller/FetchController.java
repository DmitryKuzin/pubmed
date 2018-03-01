package com.liyametrics.controller;

import com.liyametrics.service.PubmedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FetchController {

    private PubmedService pubmedService;

    @Autowired
    public FetchController(PubmedService pubmedService) {
        this.pubmedService = pubmedService;
    }

    @RequestMapping("/fetchData")
    public String fetchData() {

        String fetchedMessage = "Fetched " + pubmedService.fetchRecords().toString() + " records from PubMed";

        return fetchedMessage;
    }
}
