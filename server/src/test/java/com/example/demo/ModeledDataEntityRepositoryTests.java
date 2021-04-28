package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

@DataJpaTest
public class ModeledDataEntityRepositoryTests {

    @Autowired
    ModeledDataRepository modeledDataRepository;

    @Test
    public void whenFindByNameContainingIgnoreCase_thenFindMetrics() {
        ModeledData modeledData1 = new ModeledData("substringstart");
        ModeledData modeledData2 = new ModeledData("endsubstring");
        ModeledData modeledData3 = new ModeledData("capitalSUBSTRING");
        ModeledData modeledData4 = new ModeledData("substrin");
        modeledDataRepository.save(modeledData1);
        modeledDataRepository.save(modeledData2);
        modeledDataRepository.save(modeledData3);
        modeledDataRepository.save(modeledData4);

        Pageable pageable = PageRequest.of(0, 1);
        Page<ModeledData> modeledData = modeledDataRepository.findByNameContainingIgnoreCase("substring", pageable );

        Assert.isTrue(modeledData.getTotalElements() == 3 &&
                modeledData.getTotalPages() == 3 &&
                !modeledData.getContent().contains(modeledData4), "findByNameContainingIgnoreCase finds correct metrics");
    }
}
