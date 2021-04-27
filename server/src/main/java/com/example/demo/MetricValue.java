package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MetricValue {

    private @Id @GeneratedValue Long id;

    private Long created = new Date().getTime();
    private int value;
    private @ManyToOne Metric metric;

    public MetricValue() {}

    public MetricValue(int value, Metric metric) {
        this.value = value;
        this.metric = metric;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Long getCreated() {
        return created;
    }
}
