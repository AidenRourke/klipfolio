package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Metric {

    private @Id @GeneratedValue Long id;
    private String name;
    private String symbol;
    private @OneToMany List<MetricValue> metricValues = new ArrayList<>();

    public Metric() {}

    public Metric(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<MetricValue> getMetricValues() {
        return metricValues;
    }

    public void setMetricValues(List<MetricValue> metricValues) {
        this.metricValues = metricValues;
    }
}
