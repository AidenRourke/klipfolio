package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface MetricRepository extends PagingAndSortingRepository<Metric, Long> {
    Page<Metric> findByNameContainingIgnoreCase(Pageable pageable, String name);
}
