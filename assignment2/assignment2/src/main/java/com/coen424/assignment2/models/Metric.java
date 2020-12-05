package com.coen424.assignment2.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Metric {

    @Id
    private String metricId;
    private double cpu;
    private double mem;

    public Metric() {};

    public Metric(String metricId, double cpu, double mem) {
        this.metricId = metricId;
        this.cpu = cpu;
        this.mem = mem;
    }

    public String getMetricId() {
        return metricId;
    }

    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public double getMem() {
        return mem;
    }

    public void setMem(double mem) {
        this.mem = mem;
    }
}
