package com.liyametrics.controller;

import com.liyametrics.service.PubmedService;
import com.liyametrics.utils.DateTimeUtil;
import com.liyametrics.utils.Period;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Date;
import java.util.List;


@RestController
@Api(tags = "Fetch API", description = "Операции с подгрузкой статей")
public class FetchController {

    private PubmedService pubmedService;

    @Autowired
    public FetchController(PubmedService pubmedService) {
        this.pubmedService = pubmedService;
    }

    @ApiOperation(value = "Подгрузить статьи в пределах заданного диапазона дат")
    @GetMapping("/fetchData")
    public String fetchData(String from, String to) throws JAXBException {

        List<String> range = DateTimeUtil.getRange(from, to);

        pubmedService.fetchRecords(range);

        return "Data fetched";
    }

    @ApiOperation(value = "Подгрузить вчерашние статьи")
    @GetMapping("/fetchFreshData")
    public List<String> fetchFreshData() {

        System.out.println("updateRecords started crawling");
        return pubmedService.fetchRecords(Period.YESTERDAY);

    }
}
