package com.liyametrics.controller;

import com.liyametrics.domain.Customer;
import com.liyametrics.repository.CustomerRepository;
import com.liyametrics.service.PubmedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    private PubmedService pubmedService;

    @Autowired
    public HelloController(PubmedService pubmedService) {
        this.pubmedService = pubmedService;
    }

    @RequestMapping("/")
    public String index() {

        return "Liya super sexy!";
    }

    @RequestMapping("/fetchData")
    public String fetchData() {

        String fetchedMessage = "Fetched " + pubmedService.fetchRecords().toString() + " records from PubMed";

        return fetchedMessage;
    }

//    @RequestMapping("/getAll")
//    public Iterable<Customer> getAll() {
//        Iterable<Customer> all = customerRepository.findAll();
//
//        all.forEach(c -> {
//            System.out.printf(c.toString());
//        });
//
//        return all;
//
//    }

}