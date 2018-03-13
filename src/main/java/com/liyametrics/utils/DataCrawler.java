package com.liyametrics.utils;

import com.liyametrics.service.PubmedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataCrawler {

    private PubmedService pubmedService;
    private final int MINUTE = 1000 * 60;
    private final int HOUR = 1000 * 60 * 60;

    @Autowired
    public DataCrawler(PubmedService pubmedService) {
        this.pubmedService = pubmedService;
    }

    @Scheduled(initialDelay = MINUTE, fixedDelay = 24 * HOUR)
    public void updateRecords() {
        System.out.println("updateRecords started crawling");
        pubmedService.fetchRecords(Period.YESTERDAY);
    }

}
