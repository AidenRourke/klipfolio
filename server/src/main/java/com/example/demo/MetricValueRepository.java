package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface MetricValueRepository extends CrudRepository<MetricValue, Long> {
    MetricValue findFirstByMetricIdAndCreatedIsBeforeOrderByCreatedDesc(Long metricId, Long timeEnd);

    List<MetricValue> findAllByMetricIdAndCreatedIsBetweenOrderByCreatedAsc(Long metricId, Long timeStart, Long timeEnd);
}