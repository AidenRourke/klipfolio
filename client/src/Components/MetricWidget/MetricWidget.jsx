import React, {useState, useEffect} from "react"
import './MetricWidget.css'
import LineGraph from 'react-line-graph'
import {getLastMetricValue, getMetricValues} from "../../Api";

const MetricWidget = ({id, name, symbol}) => {
    const [latestMetricValue, setLatestMetricValue] = useState(undefined);
    const [metricValues, setMetricValues] = useState(undefined);

    useEffect(() => {
        const date = new Date();
        const timeEnd = date.getTime();
        date.setDate(date.getDate() - 7);
        const timeStart = date.getTime();

        getLastMetricValue(id, timeEnd)
            .then(res => res.json())
            .then(data => setLatestMetricValue(data));

        getMetricValues(id, timeStart, timeEnd)
            .then(res => res.json())
            .then(data => setMetricValues(data))
    }, []);

    function getData() {
        if (metricValues) {
            return metricValues._embedded.metricValues.map(metricValue => metricValue.value);
        }
        return []
    }

    const props = {
        data: getData(),
        smoothing: 0.3,
        accent: 'palevioletred',
        fillBelow: 'rgba(200,67,23,0.1)',
        hover: false,
        width: "100%",
        height: "75px"
    };

    return <div className="Metric-widget">
        <h4 className="Metric-detail">{name}</h4>
        <h2 className="Metric-detail">{symbol}{latestMetricValue?.value}</h2>
        <LineGraph {...props}/>
    </div>
};

export default MetricWidget