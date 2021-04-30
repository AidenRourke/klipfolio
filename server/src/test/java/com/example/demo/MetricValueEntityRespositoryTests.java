package com.example.demo;

import com.example.demo.metric.Metric;
import com.example.demo.metric.MetricRepository;
import com.example.demo.metricvalue.MetricValue;
import com.example.demo.metricvalue.MetricValueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@DataJpaTest
public class MetricValueEntityRespositoryTests {

    @Autowired
    MetricValueRepository metricValueRepository;

    @Autowired
    MetricRepository metricRepository;

    @Test
    public void testFindLastMetricValueByMetricIdAndTimeEnd() {
        Metric metric1 = new Metric("metric", "");

        Instant now = Instant.now();
        Instant before = now.minus(Duration.ofDays(1));
        MetricValue metricValue1 = new MetricValue(100, metric1, Date.from(before).getTime());
        before = now.minus(Duration.ofDays(3));
        MetricValue metricValue3 = new MetricValue(100, metric1, Date.from(before).getTime());
        before = now.minus(Duration.ofDays(2));
        MetricValue metricValue2 = new MetricValue(100, metric1, Date.from(before).getTime());
        before = now.minus(Duration.ofDays(4));
        MetricValue metricValue4 = new MetricValue(100, metric1, Date.from(before).getTime());

        metric1.getMetricValues().add(metricValue1);
        metric1.getMetricValues().add(metricValue2);
        metric1.getMetricValues().add(metricValue3);
        metric1.getMetricValues().add(metricValue4);

        metricRepository.save(metric1);

        Instant timeEnd = now.minus(Duration.ofHours(25));
        MetricValue metricValue = metricValueRepository.findFirstByMetricIdAndCreatedIsBeforeOrderByCreatedDesc(metric1.getId(), Date.from(timeEnd).getTime());

        Assert.isTrue(metricValue.equals(metricValue2), "findFirstByMetricIdAndCreatedIsBeforeOrderByCreatedDesc finds last Metric Value");
    }

    @Test
    public void testFindMetricValuesByMetricIdAndTimeRange() {
        Metric metric1 = new Metric("metric", "");

        Instant now = Instant.now();
        Instant before = now.minus(Duration.ofHours(1));
        MetricValue metricValue1 = new MetricValue(100, metric1, Date.from(before).getTime());
        before = now.minus(Duration.ofHours(7));
        MetricValue metricValue2 = new MetricValue(100, metric1, Date.from(before).getTime());
        before = now.minus(Duration.ofHours(4));
        MetricValue metricValue3 = new MetricValue(100, metric1, Date.from(before).getTime());
        before = now.minus(Duration.ofHours(10));
        MetricValue metricValue4 = new MetricValue(100, metric1, Date.from(before).getTime());

        metric1.getMetricValues().add(metricValue1);
        metric1.getMetricValues().add(metricValue2);
        metric1.getMetricValues().add(metricValue3);
        metric1.getMetricValues().add(metricValue4);

        metricRepository.save(metric1);

        Instant timeEnd = now.minus(Duration.ofHours(2));
        Instant timeStart = now.minus(Duration.ofHours(8));
        List<MetricValue> metricValues = metricValueRepository.findAllByMetricIdAndCreatedIsBetweenOrderByCreatedAsc(metric1.getId(), Date.from(timeStart).getTime(), Date.from(timeEnd).getTime());

        Assert.isTrue(metricValues.size() == 2, "findAllByMetricIdAndCreatedIsBetween finds correct Metric Values");
        Assert.isTrue(metricValues.get(0).equals(metricValue2) && metricValues.get(1).equals(metricValue3), "findAllByMetricIdAndCreatedIsBetween correctlycorrectly orders Metric Values");
    }
}
