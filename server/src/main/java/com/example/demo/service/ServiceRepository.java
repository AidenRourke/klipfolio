package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface ServiceRepository extends PagingAndSortingRepository<Service, Long> {
    Page<Service> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
