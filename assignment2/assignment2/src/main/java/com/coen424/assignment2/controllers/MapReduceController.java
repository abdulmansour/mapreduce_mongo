package com.coen424.assignment2.controllers;

import com.coen424.assignment2.dao.MetricRepository;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mapreduce")
public class MapReduceController {

    @Autowired
    private MetricRepository metricRepository;

    @GetMapping("/")
    public List<BasicDBObject> mapReduce(){
        return this.metricRepository.mapReduceMetrics();
    }

}
