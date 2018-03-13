package com.liyametrics.controller;

import com.liyametrics.dao.RecordDAO;
import com.liyametrics.domain.Record;
import com.liyametrics.service.PubmedService;
import com.liyametrics.service.RecordService;
import com.liyametrics.utils.DateTimeUtil;
import com.liyametrics.utils.Period;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.event.PaintEvent;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Article API", description = "Операции со статьями")
public class ArticleController {
    private RecordService recordService;
    private PubmedService pubmedService;

    @Autowired
    public ArticleController(RecordService recordService, PubmedService pubmedService) {
        this.recordService = recordService;
        this.pubmedService = pubmedService;
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
