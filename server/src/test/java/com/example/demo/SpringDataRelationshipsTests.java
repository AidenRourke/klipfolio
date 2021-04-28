package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public void whenCreateMetricValueWithoutMetric_thenError() {
        MetricValue metricValue1 = new MetricValue(100, null, new Date().getTime());

        String error = "";
        try {
            metricValueRepository.save(metricValue1);
        } catch (Exception e) {
            error = e.getMessage();
        }

        Assert.isTrue(error.contains("not-null property references a null or transient value"), "Cannot create Metric Value without Metric");
    }

    @Test
    public void whenDeleteMetric_thenDeleteMetricValues() {
        Metric metric1 = new Metric("metric", "");
        metricRepository.save(metric1);
        MetricValue metricValue1 = new MetricValue(100, metric1, new Date().getTime());
        MetricValue metricValue2 = new MetricValue(100, metric1, new Date().getTime());
        MetricValue metricValue3 = new MetricValue(100, metric1, new Date().getTime());
        MetricValue metricValue4 = new MetricValue(100, metric1, new Date().getTime());
        metricValueRepository.save(metricValue1);
        metricValueRepository.save(metricValue2);
        metricValueRepository.save(metricValue3);
        metricValueRepository.save(metricValue4);

        metricRepository.deleteAll();

        Assert.isTrue(metricValueRepository.count() == 0, "Deleting metric deletes values");
    }
}
