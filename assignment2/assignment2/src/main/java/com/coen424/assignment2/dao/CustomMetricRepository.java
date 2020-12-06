package com.coen424.assignment2.dao;

import com.mongodb.BasicDBObject;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;

import java.util.List;

public interface CustomMetricRepository {
    List<BasicDBObject> mapReduceMetrics();
}
