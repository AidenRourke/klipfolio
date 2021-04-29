package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

@DataJpaTest
public class ServiceEntityRepositoryTests {

    @Autowired ServiceRepository serviceRepository;

    @Test
    public void testFindServicesByName() {
        Service service1 = new Service("substringstart", "example.com");
        Service service2 = new Service("endsubstring", "example.com");
        Service service3 = new Service("capitalSUBSTRING", "example.com");
        Service service4 = new Service("substrin", "");
        serviceRepository.save(service1);
        serviceRepository.save(service2);
        serviceRepository.save(service3);
        serviceRepository.save(service4);

        Pageable pageable = PageRequest.of(0, 1);
        Page<Service> services = serviceRepository.findByNameContainingIgnoreCase("substring", pageable );

        Assert.isTrue(services.getTotalElements() == 3 &&
                services.getTotalPages() == 3 &&
                !services.getContent().contains(service4), "findByNameContainingIgnoreCase finds correct Services");
    }
}
