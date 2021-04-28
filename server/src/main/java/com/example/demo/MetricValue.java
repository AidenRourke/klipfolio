package com.example.demo;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class MetricValue {

    private @Id @GeneratedValue Long id;

    private Long created;
    private int value;
    private @ManyToOne Metric metric;

    public MetricValue() {
        created = new Date().getTime();
    }

    public MetricValue(int value, Metric metric) {
        this();
        this.value = value;
        this.metric = metric;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricValue that = (MetricValue) o;
        return value == that.value &&
                Objects.equals(id, that.id) &&
                Objects.equals(created, that.created) &&
                Objects.equals(metric, that.metric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, value, metric);
    }

    @Override
    public String toString() {
        return "MetricValue{" +
                "id=" + id +
                ", created=" + created +
                ", value=" + value +
                ", metric=" + metric +
                '}';
    }
}