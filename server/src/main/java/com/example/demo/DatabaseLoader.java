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


    @Autowired
    public DatabaseLoader(MetricRepository metrics, MetricValueRepository metricValues, ServiceRepository services) {
        this.metrics = metrics;
        this.metricValues = metricValues;
        this.services = services;
    }

    @Override
    public void run(String... strings) throws Exception {
        Metric metric1 = new Metric("Profit", "$");
        this.metrics.save(metric1);
        List<MetricValue> metricValues1 = new ArrayList<>();
        metricValues1.add(this.metricValues.save(new MetricValue(1000, metric1, new Date().getTime())));
        metricValues1.add(this.metricValues.save(new MetricValue(2000, metric1, new Date().getTime())));
        metricValues1.add(this.metricValues.save(new MetricValue(3000, metric1, new Date().getTime())));
        metricValues1.add(this.metricValues.save(new MetricValue(4000, metric1, new Date().getTime())));
        metricValues1.add(this.metricValues.save(new MetricValue(10000, metric1, new Date().getTime())));
        metricValues1.add(this.metricValues.save(new MetricValue(9000, metric1, new Date().getTime())));
        metricValues1.add(this.metricValues.save(new MetricValue(8000, metric1, new Date().getTime())));
        metricValues1.add(this.metricValues.save(new MetricValue(10000, metric1, new Date().getTime())));
        metric1.setMetricValues(metricValues1);
        this.metrics.save(metric1);

        Metric metric2 = new Metric("Views", "");
        this.metrics.save(metric2);
        List<MetricValue> metricValues2 = new ArrayList<>();
        metricValues2.add(this.metricValues.save(new MetricValue(100, metric2, new Date().getTime())));
        metricValues2.add(this.metricValues.save(new MetricValue(200, metric2, new Date().getTime())));
        metricValues2.add(this.metricValues.save(new MetricValue(300, metric2, new Date().getTime())));
        metricValues2.add(this.metricValues.save(new MetricValue(400, metric2, new Date().getTime())));
        metricValues2.add(this.metricValues.save(new MetricValue(1000, metric2, new Date().getTime())));
        metricValues2.add(this.metricValues.save(new MetricValue(2000, metric2, new Date().getTime())));
        metricValues2.add(this.metricValues.save(new MetricValue(3000, metric2, new Date().getTime())));
        metricValues2.add(this.metricValues.save(new MetricValue(5000, metric2, new Date().getTime())));
        metric2.setMetricValues(metricValues2);
        this.metrics.save(metric2);

        this.services.save(new Service("Dropbox", "https://icon-svgs.s3.amazonaws.com/Dropbox_Icon.svg"));
        this.services.save(new Service("Facebook", "https://icon-svgs.s3.amazonaws.com/Facebook_Icon.svg"));
        this.services.save(new Service("Google Drive", "https://icon-svgs.s3.amazonaws.com/Google_Drive_Icon.svg"));


    }

}
