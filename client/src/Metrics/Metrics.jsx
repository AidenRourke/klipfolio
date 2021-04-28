import React, {useState, useEffect} from "react"

import './Metrics.css'

import {MetricWidget, Button} from "../Components";
import {getMetrics} from "../Api";

const Metrics = () => {
    const [metrics, setMetrics] = useState(undefined);

    useEffect(() => {
        getMetrics()
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
            return <Button onClick={() => fetchMore(metrics._links.next.href)}>More metrics</Button>
        }
    };

    return <div>
        <h3 className="Metrics-title">Recommended Metrics</h3>
        <div className="Asset-container">
            {renderContent()}
        </div>
        <div className="Load-more">
            {renderLoadMore()}
        </div>
    </div>
};

export default Metrics;
