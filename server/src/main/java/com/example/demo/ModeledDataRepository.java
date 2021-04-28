package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface ModeledDataRepository extends PagingAndSortingRepository<ModeledData, Long> {
    Page<ModeledData> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
