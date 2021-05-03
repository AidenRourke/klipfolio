package com.example.demo.metric;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface MetricRepository extends PagingAndSortingRepository<Metric, Long> {
    Page<Metric> findByNameContainingIgnoreCase(@Param("name") String name, @Param("pageable") Pageable pageable);
}
