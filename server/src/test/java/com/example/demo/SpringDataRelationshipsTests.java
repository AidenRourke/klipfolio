package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.Date;

@DataJpaTest
public class SpringDataRelationshipsTests {

    @Autowired MetricValueRepository metricValueRepository;

    @Autowired MetricRepository metricRepository;

    @Test
    public void whenCreateMetricWithoutMetricValues_thenCreateMetric() {
        Metric metric1 = new Metric("metric", "");

        metricRepository.save(metric1);

        Assert.notNull(metric1.getId(), "Can create Metric without Metric Value");
    }

    @Test()
    public void whenSaveMetricValueWithoutMetric_thenError() {
        MetricValue metricValue1 = new MetricValue(100, null, new Date().getTime());

        String error = "";
        try {
            metricValueRepository.save(metricValue1);
        } catch (Exception e) {
            error = e.getMessage();
        }

        Assert.isTrue(error.contains("not-null property references a null or transient value"), "Cannot have Metric Value without a Metric");
    }

    @Test
    public void whenDeleteMetric_thenDeleteMetricValues() {
        Metric metric1 = new Metric("metric", "");

        metric1.getMetricValues().add(new MetricValue(100, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(100, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(100, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(100, metric1, new Date().getTime()));

        metricRepository.save(metric1);

        metricRepository.delete(metric1);

        Assert.isTrue(metricValueRepository.count() == 0, "Deleting metric deletes values");
    }
}
