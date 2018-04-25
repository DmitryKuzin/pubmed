package com.liyametrics.controller;

import com.liyametrics.service.FetchService;
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

    private FetchService fetchService;

    @Autowired
    public FetchController(FetchService fetchService) {
        this.fetchService = fetchService;
    }

    @ApiOperation(value = "Подгрузить статьи в пределах заданного диапазона дат")
    @GetMapping("/fetchDataByRange")
    public String fetchData(String from, String to) throws JAXBException {

        List<String> range = DateTimeUtil.getRange(from, to);

        fetchService.fetch(range);

        return "Data fetched";
    }

    @ApiOperation(value = "Подгрузить вчерашние статьи")
    @GetMapping("/fetchYesterdayData")
    public String fetchYesterdayData() {

        System.out.println("updateRecords started crawling");
        fetchService.fetch(Period.YESTERDAY);

        return "Data fetched";
    }
}
