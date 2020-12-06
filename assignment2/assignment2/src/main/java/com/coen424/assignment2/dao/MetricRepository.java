package com.coen424.assignment2.dao;

import com.coen424.assignment2.models.Metric;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends MongoRepository<Metric, Long>, CustomMetricRepository {
}
