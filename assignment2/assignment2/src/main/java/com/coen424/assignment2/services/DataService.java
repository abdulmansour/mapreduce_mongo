package com.coen424.assignment2.services;

import com.coen424.assignment2.dao.MetricRepository;
import com.coen424.assignment2.models.Metric;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class DataService {

    private String line = "";
    private String spliter = ",";

    @Autowired
    private MetricRepository metricRepository;
    private List<Metric> metrics;

    public DataService() {

        metrics = metricRepository.findAll();

        if (metrics.isEmpty()) {
            List<String> list = Arrays.asList("DVD-testing", "DVD-training");
            long i = 0;
            for (String file : list) {
                try {
                    File resource = new ClassPathResource("benchmarks/" + file + ".csv").getFile();
                    BufferedReader br = new BufferedReader(new FileReader(resource));
                    br.readLine(); // skip first line

                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(spliter);
                        Metric m = new Metric(String.valueOf(i),Integer.parseInt(data[0]), Double.parseDouble(data[3]));
                        metricRepository.save(m);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            metrics = metricRepository.findAll();
            System.out.println("Added " + metrics.size() + " metrics to Metric Collection!");
        }
        else {
            System.out.println("Metric Collection already contains " + metrics.size() + " metrics!");
        }
    }
}
