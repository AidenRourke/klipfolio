package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final MetricRepository metrics;
    private final MetricValueRepository metricValues;
    private final ServiceRepository services;
    private final ModeledDataRepository modeledDataRepository;


    @Autowired
    public DatabaseLoader(MetricRepository metrics, MetricValueRepository metricValues, ServiceRepository services, ModeledDataRepository modeledDataRepository) {
        this.metrics = metrics;
        this.metricValues = metricValues;
        this.services = services;
        this.modeledDataRepository = modeledDataRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Metric metric1 = new Metric("Profit", "$");

        metric1.getMetricValues().add(new MetricValue(1000, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(2000, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(3000, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(4000, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(10000, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(9000, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(8000, metric1, new Date().getTime()));
        metric1.getMetricValues().add(new MetricValue(10000, metric1, new Date().getTime()));

        this.metrics.save(metric1);

        Metric metric2 = new Metric("Views", "");

        metric2.getMetricValues().add(new MetricValue(100, metric2, new Date().getTime()));
        metric2.getMetricValues().add(new MetricValue(200, metric2, new Date().getTime()));
        metric2.getMetricValues().add(new MetricValue(300, metric2, new Date().getTime()));
        metric2.getMetricValues().add(new MetricValue(400, metric2, new Date().getTime()));
        metric2.getMetricValues().add(new MetricValue(1000, metric2, new Date().getTime()));
        metric2.getMetricValues().add(new MetricValue(2000, metric2, new Date().getTime()));
        metric2.getMetricValues().add(new MetricValue(3000, metric2, new Date().getTime()));
        metric2.getMetricValues().add(new MetricValue(5000, metric2, new Date().getTime()));

        this.metrics.save(metric2);

        Metric metric3 = new Metric("Revenue", "$");

        metric3.getMetricValues().add(new MetricValue(5000, metric3, new Date().getTime()));
        metric3.getMetricValues().add(new MetricValue(4500, metric3, new Date().getTime()));
        metric3.getMetricValues().add(new MetricValue(5000, metric3, new Date().getTime()));
        metric3.getMetricValues().add(new MetricValue(2000, metric3, new Date().getTime()));
        metric3.getMetricValues().add(new MetricValue(500, metric3, new Date().getTime()));
        metric3.getMetricValues().add(new MetricValue(50, metric3, new Date().getTime()));
        metric3.getMetricValues().add(new MetricValue(100, metric3, new Date().getTime()));
        metric3.getMetricValues().add(new MetricValue(500, metric3, new Date().getTime()));

        this.metrics.save(metric3);

        this.services.save(new Service("Dropbox", "https://icon-svgs.s3.amazonaws.com/Dropbox_Icon.svg"));
        this.services.save(new Service("Facebook", "https://icon-svgs.s3.amazonaws.com/Facebook_Icon.svg"));
        this.services.save(new Service("Google Drive", "https://icon-svgs.s3.amazonaws.com/Google_Drive_Icon.svg"));


        ModeledData modeledData1 = new ModeledData("Lead");
        ModeledData modeledData2 = new ModeledData("Sales Target");
        ModeledData modeledData3 = new ModeledData("Retention Expansion Data");
        ModeledData modeledData4 = new ModeledData("LVT to CAC Data");
        modeledDataRepository.save(modeledData1);
        modeledDataRepository.save(modeledData2);
        modeledDataRepository.save(modeledData3);
        modeledDataRepository.save(modeledData4);
    }

}
