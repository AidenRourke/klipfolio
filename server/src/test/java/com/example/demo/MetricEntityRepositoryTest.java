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
        Metric metric4 = new Metric("substrin", "");
        metricRepository.save(metric1);
        metricRepository.save(metric2);
        metricRepository.save(metric3);
        metricRepository.save(metric4);

        Pageable pageable = PageRequest.of(0, 1);
        Page<Metric> metrics = metricRepository.findByNameContainingIgnoreCase("substring", pageable );

        Assert.isTrue(metrics.getTotalElements() == 3 &&
                metrics.getTotalPages() == 3 &&
                !metrics.getContent().contains(metric4), "findByNameContainingIgnoreCase finds correct metrics");
    }
}
