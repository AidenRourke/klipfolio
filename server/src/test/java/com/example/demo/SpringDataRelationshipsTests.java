package com.example.demo;

import com.example.demo.metric.Metric;
import com.example.demo.metric.MetricRepository;
import com.example.demo.metricvalue.MetricValue;
import com.example.demo.metricvalue.MetricValueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.Date;

@DataJpaTest
public class SpringDataRelationshipsTests {

    @Autowired
    MetricValueRepository metricValueRepository;

    @Autowired
    MetricRepository metricRepository;

    @Test
    public void testNonNullMetric() {
        MetricValue metricValue1 = new MetricValue(100, null, new Date().getTime());

        String error = "";
        try {
            metricValueRepository.save(metricValue1);
        } catch (Exception e) {
            error = e.getMessage();
        }

        Assert.isTrue(error.contains("not-null property references a null or transient value"), "Metric Value cannot have null Metric");
    }

    @Test
    public void testOrphanRemoval() {
        Metric metric1 = new Metric("metric", "");

        metric1.getMetricValues().add((new MetricValue(100, metric1, new Date().getTime())));

        metricRepository.save(metric1);

        metric1.getMetricValues().clear();

        Assert.isTrue(metricValueRepository.count() == 0, "Deletes orphans");
    }

    @Test
    public void testCascadeDelete() {
        Metric metric1 = new Metric("metric", "");

        metric1.getMetricValues().add(new MetricValue(100, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(100, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(100, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(100, metric1, new Date().getTime()));

        metricRepository.save(metric1);

        metricRepository.delete(metric1);

        Assert.isTrue(metricValueRepository.count() == 0, "Deleting Metric cascades to Metric Values");
    }
}
