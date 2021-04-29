package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
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

        Instant now = Instant.now();
        Instant before = now.minus(Duration.ofDays(1));

        metric1.getMetricValues().add(new MetricValue(1000, metric1, Date.from(before).getTime()));
        metric1.getMetricValues().add(new MetricValue(2000, metric1, Date.from(before).getTime()));
        metric1.getMetricValues().add(new MetricValue(3000, metric1, Date.from(before).getTime()));
        metric1.getMetricValues().add(new MetricValue(4000, metric1, Date.from(before).getTime()));
        metric1.getMetricValues().add(new MetricValue(10000, metric1, Date.from(before).getTime()));
        metric1.getMetricValues().add(new MetricValue(9000, metric1, Date.from(before).getTime()));
        metric1.getMetricValues().add(new MetricValue(8000, metric1, Date.from(before).getTime()));
        metric1.getMetricValues().add(new MetricValue(10000, metric1, Date.from(now).getTime()));

        this.metrics.save(metric1);

        Metric metric2 = new Metric("Views", "");

        metric2.getMetricValues().add(new MetricValue(100, metric2, Date.from(before).getTime()));
        metric2.getMetricValues().add(new MetricValue(200, metric2, Date.from(before).getTime()));
        metric2.getMetricValues().add(new MetricValue(300, metric2, Date.from(before).getTime()));
        metric2.getMetricValues().add(new MetricValue(400, metric2, Date.from(before).getTime()));
        metric2.getMetricValues().add(new MetricValue(1000, metric2, Date.from(before).getTime()));
        metric2.getMetricValues().add(new MetricValue(2000, metric2, Date.from(before).getTime()));
        metric2.getMetricValues().add(new MetricValue(3000, metric2, Date.from(before).getTime()));
        metric2.getMetricValues().add(new MetricValue(5000, metric2, Date.from(now).getTime()));

        this.metrics.save(metric2);

        Metric metric3 = new Metric("Revenue", "$");

        metric3.getMetricValues().add(new MetricValue(5000, metric3, Date.from(before).getTime()));
        metric3.getMetricValues().add(new MetricValue(4500, metric3, Date.from(before).getTime()));
        metric3.getMetricValues().add(new MetricValue(5000, metric3, Date.from(before).getTime()));
        metric3.getMetricValues().add(new MetricValue(2000, metric3, Date.from(before).getTime()));
        metric3.getMetricValues().add(new MetricValue(500, metric3, Date.from(before).getTime()));
        metric3.getMetricValues().add(new MetricValue(50, metric3, Date.from(before).getTime()));
        metric3.getMetricValues().add(new MetricValue(100, metric3, Date.from(before).getTime()));
        metric3.getMetricValues().add(new MetricValue(500, metric3, Date.from(now).getTime()));

        this.metrics.save(metric3);

        Metric metric4 = new Metric("MRR", "$");

        metric4.getMetricValues().add(new MetricValue(400, metric4, Date.from(before).getTime()));
        metric4.getMetricValues().add(new MetricValue(3000, metric4, Date.from(before).getTime()));
        metric4.getMetricValues().add(new MetricValue(2500, metric4, Date.from(before).getTime()));
        metric4.getMetricValues().add(new MetricValue(4000, metric4, Date.from(before).getTime()));
        metric4.getMetricValues().add(new MetricValue(3500, metric4, Date.from(before).getTime()));
        metric4.getMetricValues().add(new MetricValue(1000, metric4, Date.from(before).getTime()));
        metric4.getMetricValues().add(new MetricValue(4000, metric4, Date.from(before).getTime()));
        metric4.getMetricValues().add(new MetricValue(3000, metric4, Date.from(now).getTime()));

        this.metrics.save(metric4);

        Metric metric5 = new Metric("Subscribers", "");

        metric5.getMetricValues().add(new MetricValue(150, metric5, Date.from(before).getTime()));
        metric5.getMetricValues().add(new MetricValue(200, metric5, Date.from(before).getTime()));
        metric5.getMetricValues().add(new MetricValue(300, metric5, Date.from(before).getTime()));
        metric5.getMetricValues().add(new MetricValue(340, metric5, Date.from(before).getTime()));
        metric5.getMetricValues().add(new MetricValue(500, metric5, Date.from(before).getTime()));
        metric5.getMetricValues().add(new MetricValue(300, metric5, Date.from(before).getTime()));
        metric5.getMetricValues().add(new MetricValue(600, metric5, Date.from(before).getTime()));
        metric5.getMetricValues().add(new MetricValue(650, metric5, Date.from(now).getTime()));

        this.metrics.save(metric5);

        Metric metric6 = new Metric("Debt Equity Ratio", "");

        metric6.getMetricValues().add(new MetricValue(1.0, metric6, Date.from(before).getTime()));
        metric6.getMetricValues().add(new MetricValue(0.5, metric6, Date.from(before).getTime()));
        metric6.getMetricValues().add(new MetricValue(0.7, metric6, Date.from(before).getTime()));
        metric6.getMetricValues().add(new MetricValue(1.5, metric6, Date.from(before).getTime()));
        metric6.getMetricValues().add(new MetricValue(1.2, metric6, Date.from(before).getTime()));
        metric6.getMetricValues().add(new MetricValue(1.0, metric6, Date.from(before).getTime()));
        metric6.getMetricValues().add(new MetricValue(2.0, metric6, Date.from(before).getTime()));
        metric6.getMetricValues().add(new MetricValue(0.9, metric6, Date.from(now).getTime()));

        this.metrics.save(metric6);



        this.services.save(new Service("Dropbox", "https://icon-svgs.s3.amazonaws.com/Dropbox_Icon.svg"));
        this.services.save(new Service("Facebook", "https://icon-svgs.s3.amazonaws.com/Facebook_Icon.svg"));
        this.services.save(new Service("Google Drive", "https://icon-svgs.s3.amazonaws.com/Google_Drive_Icon.svg"));
        this.services.save(new Service("Slack", "https://icon-svgs.s3.amazonaws.com/Slack_Icon.svg"));


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
