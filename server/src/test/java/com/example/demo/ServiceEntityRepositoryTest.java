package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

@DataJpaTest
public class ServiceEntityRepositoryTest {

    @Autowired ServiceRepository serviceRepository;

    @Test
    public void whenFindByNameContainingIgnoreCase_thenFindMetrics() {
        Service service1 = new Service("substringstart", "example.com");
        Service service2 = new Service("endsubstring", "example.com");
        Service service3 = new Service("capitalSUBSTRING", "example.com");
        serviceRepository.save(service1);
        serviceRepository.save(service2);
        serviceRepository.save(service3);

        Page<Service> services = serviceRepository.findByNameContainingIgnoreCase("substring", null );

        Assert.isTrue(services.getTotalElements() == 3, "findByNameContainingIgnoreCase finds correct services");
    }

    @Test
    public void whenFindbyNameContainingIgnoreCase_thenIgnoreMetrics() {
        Service service1 = new Service("substrin", "");
        serviceRepository.save(service1);

        Page<Service> services = serviceRepository.findByNameContainingIgnoreCase("substring", null );

        Assert.isTrue(services.getTotalElements() == 0, "findByNameContainingIgnoreCase ignores correct services");
    }

    @Test
    public void whenFindbyNameContainingIgnoreCase_thenPaginateServices() {
        Service service1 = new Service("substringstart", "");
        Service service2 = new Service("endsubstring", "");
        serviceRepository.save(service1);
        serviceRepository.save(service2);

        Pageable pageable = PageRequest.of(0, 1);
        Page<Service> services = serviceRepository.findByNameContainingIgnoreCase("substring", pageable);

        Assert.isTrue(services.getTotalPages() == 2, "findByNameContainingIgnoreCase paginates services");
    }
}
