package com.example.demo.modelleddata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface ModeledDataRepository extends PagingAndSortingRepository<ModeledData, Long> {
    Page<ModeledData> findByNameContainingIgnoreCase(@Param("name") String name, @Param("pageable") Pageable pageable);
}
