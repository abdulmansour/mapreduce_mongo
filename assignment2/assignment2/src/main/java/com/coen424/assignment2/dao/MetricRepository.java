package com.coen424.assignment2.dao;

import com.coen424.assignment2.models.Metric;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetricRepository extends MongoRepository<Metric, String> {
}
