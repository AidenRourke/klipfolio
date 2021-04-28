package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface MetricValueRepository extends CrudRepository<MetricValue, Long> {
    MetricValue findFirstByMetricIdAndCreatedIsBeforeOrderByCreatedDesc(@Param("metricId") Long metricId, @Param("timeEnd") Long timeEnd);

    List<MetricValue> findAllByMetricIdAndCreatedIsBetweenOrderByCreatedAsc(@Param("metricId") Long metricId, @Param("timeStart") Long timeStart, @Param("timeEnd")  Long timeEnd);
}