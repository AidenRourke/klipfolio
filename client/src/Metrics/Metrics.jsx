import React, {useState, useEffect} from "react"

import './Metrics.css'

import {MetricWidget, Button} from "../Components";

const Metrics = () => {
    const [metrics, setMetrics] = useState(undefined);

    useEffect(() => {
        fetch("http://localhost:8080/metrics?size=1")
            .then(res => res.json())
            .then(data => setMetrics(data))
    }, []);

    const renderContent = () => {
        return metrics?._embedded.metrics.map(metric =>
            <MetricWidget key={metric._links.self.href} name={metric.name} id={metric.id} symbol={metric.symbol}/>
        )
    };

    const fetchMore = (url) => {
        fetch(url)
            .then(res => res.json())
            .then(newData => {
                const prevMetrics = metrics._embedded.metrics;
                const newMetrics = newData._embedded.metrics;
                const updatedMetrics = [...prevMetrics, ...newMetrics];
                const updatedData = newData;
                updatedData._embedded.metrics = updatedMetrics;
                setMetrics(updatedData)
            })
    };

    const renderLoadMore = () => {
        if (metrics?._links?.next) {
            return <div className="Load-more">
                <Button onClick={() => fetchMore(metrics._links.next.href)}>More metrics</Button>
            </div>
        }
    };

    return <div>
        <h3>Recommended Metrics</h3>
        <div className="Asset-container">
            {renderContent()}
        </div>
        {renderLoadMore()}
    </div>
};

export default Metrics;
