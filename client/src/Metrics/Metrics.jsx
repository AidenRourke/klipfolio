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
            .then(res => {
                const prevMetrics = metrics._embedded.metrics;
                const newMetrics = res._embedded.metrics;
                res._embedded.metrics = [...prevMetrics, ...newMetrics];
                setMetrics(res);
            })
    };

    const fetchFirst = (url) => {
        fetch(url)
            .then(res => res.json())
            .then(res => {
                setMetrics(res)
            })
    };

    const renderLoadLess = () => {
        if (metrics?.page.number > 0) {
            return <Button onClick={() => fetchFirst(metrics._links.first.href)}>Less metrics</Button>
        }
    };

    const renderLoadMore = () => {
        if (metrics?.page.number !== metrics?.page.totalPages - 1) {
            return <Button onClick={() => fetchMore(metrics._links.next.href)}>More metrics</Button>
        }
    };

    return <div>
        <h3 className="Asset-title">Recommended Metrics</h3>
        <div className="Metric-grid">
            {renderContent()}
        </div>
        <div className="Load-more">
            {renderLoadLess()}
            {renderLoadMore()}
        </div>
    </div>
};

export default Metrics;
