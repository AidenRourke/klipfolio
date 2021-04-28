package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

@DataJpaTest
public class MetricEntityRepositoryTest {

    @Autowired MetricRepository metricRepository;

    @Test
    public void whenFindByNameContainingIgnoreCase_thenFindMetrics() {
        Metric metric1 = new Metric("substringstart", "");
        Metric metric2 = new Metric("endsubstring", "");
        Metric metric3 = new Metric("capitalSUBSTRING", "");
        metricRepository.save(metric1);
        metricRepository.save(metric2);
        metricRepository.save(metric3);

        Page<Metric> metrics = metricRepository.findByNameContainingIgnoreCase("substring", null );

        Assert.isTrue(metrics.getTotalElements() == 3, "findByNameContainingIgnoreCase finds correct metrics");
    }

    @Test
    public void whenFindbyNameContainingIgnoreCase_thenIgnoreMetrics() {
        Metric metric1 = new Metric("substrin", "");
        metricRepository.save(metric1);

        Page<Metric> metrics = metricRepository.findByNameContainingIgnoreCase("substring", null );

        Assert.isTrue(metrics.getTotalElements() == 0, "findByNameContainingIgnoreCase ignores correct metrics");
    }

    @Test
    public void whenFindbyNameContainingIgnoreCase_thenPaginateMetrics() {
        Metric metric1 = new Metric("substringstart", "");
        Metric metric2 = new Metric("endsubstring", "");
        metricRepository.save(metric1);
        metricRepository.save(metric2);

        Pageable pageable = PageRequest.of(0, 1);
        Page<Metric> metrics = metricRepository.findByNameContainingIgnoreCase("substring", pageable);

        Assert.isTrue(metrics.getTotalPages() == 2, "findByNameContainingIgnoreCase paginates metrics");
    }
}
