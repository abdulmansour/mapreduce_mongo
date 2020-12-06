package com.coen424.assignment2.dao;

import com.coen424.assignment2.models.Metric;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomMetricRepositoryImpl implements CustomMetricRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<BasicDBObject> mapReduceMetrics() {
        String mapFunction = "function() { emit(parseInt(this.cpu/10), {count:1, mem:this.mem}); }";
        String reduceFunction =
                "function(cpu, values) { var total = 0; var max_mem = 0; var min_mem = 1000000;" +
                "   for (var i = 0; i < values.length; i++) {" +
                "       total += values[i].count;" +
                        "if (values[i].mem > max_mem) max_mem = values[i].mem;" +
                        "if (values[i].mem < min_mem) min_mem = values[i].mem;" +
                "   }" +
                "   return {count:total, max_mem: max_mem, min_mem: min_mem};" +
                "}";
        MapReduceResults<BasicDBObject> mapReduceResults = mongoTemplate.mapReduce("metric", mapFunction, reduceFunction, BasicDBObject.class);
        List<BasicDBObject> results = new ArrayList<>();
        for (BasicDBObject b:mapReduceResults) {
            results.add(b);
            System.out.println(b.toString());
        }
        return results;
    }
}
