package com.example.demo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface ServiceRepository extends PagingAndSortingRepository<Service, Long> {
    List<Service> findByNameContainingIgnoreCase(String name);
}
