package com.liyametrics.controller;

import com.liyametrics.domain.Record;
import com.liyametrics.service.RecordService;
import com.liyametrics.utils.Period;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Article API", description = "Операции со статьями")
public class ArticleController {
    private RecordService recordService;

    @Autowired
    public ArticleController(RecordService recordService) {
        this.recordService = recordService;
    }


    @ApiOperation(value = "Получить статью по id", response = Record.class)
    @GetMapping("getArticles")
    public Record getArticles(String id) {

        return recordService.getRecord(id);

    }

    @ApiOperation(value = "Получить отсортированный по убыванию популярности список статей")
    @GetMapping("top")
    public List<Record> topRecords(Period period, Integer limit) {
        return recordService.getTopRecordsByPeriod(period, limit);
    }


}
