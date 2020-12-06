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
        String mapFunction = "function() { " + "var group;"
                + "if(this.cpu < 11) {" + "group = '(0-10]';" + "emit(group,this.mem);" + "}"
                + "else if(this.cpu < 21) {" + "group = '(11-20]';" + "emit(group,this.mem);" + "}"
                + "else if(this.cpu < 31) {" + "group = '(21-30]';" + "emit(group,this.mem);" + "}"
                + "else if(this.cpu < 41) {" + "group = '(31-40]';" + "emit(group,this.mem);" + "}"
                + "else if(this.cpu < 51) {" + "group = '(41-50]';" + "emit(group,this.mem);" + "}"
                + "else if(this.cpu < 61) {" + "group = '(51-60]';" + "emit(group,this.mem);" + "}"
                + "else if(this.cpu < 71) {" + "group = '(61-70]';" + "emit(group,this.mem);" + "}"
                + "else if(this.cpu < 81) {" + "group = '(71-80]';" + "emit(group,this.mem);" + "}"
                + "else if(this.cpu < 91) {" + "group = '(81-90]';" + "emit(group,this.mem);" + "}"
                + "else {" + "group = '(91-100]';" + "emit(group,this.mem);" + "}" + "};";
        String reduceFunction =
                "function(group, values) { var total = 0; var avg = 0; median =0; var variance = 0; var middle = 0; var sdev = 0; max_mem = 0; var min_mem = 1000000;" +
                        "values.sort();" +
                "   for (var i = 0; i < values.length; i++) {" +
                "       total += values[i];" +

                "   }" +
                        "avg = total/ values.length;"
                        + "middle = Math.floor(values.length/2);"
                        + "min_mem = values[0];"
                        + "max_mem = values[values.length-1];"
                        + "for (var j = 0; j < values.length; j++) {" +
                        "       variance += (values[j] - avg)*(values[j] - avg);" + "}"
                        + "median = values[middle];"
                        + "sdev = Math.sqrt(variance/values.length);" +
                "   return {count:values.length, average: avg, median: median, standard_deviation: sdev, max: max_mem, min: min_mem};" +
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

