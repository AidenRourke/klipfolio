package com.example.demo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface MetricRepository extends PagingAndSortingRepository<Metric, Long> {
    List<Metric> findByNameContainingIgnoreCase(String name);
}
