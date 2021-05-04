package com.example.demo.metric;

import com.example.demo.metricvalue.MetricValue;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Metric {

    private @Id @GeneratedValue Long id;
    private String name;
    private String symbol;

    public Metric(){
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metric metric = (Metric) o;
        return Objects.equals(id, metric.id) &&
                Objects.equals(name, metric.name) &&
                Objects.equals(symbol, metric.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symbol);
    }

    @Override
    public String toString() {
        return "Metric{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}