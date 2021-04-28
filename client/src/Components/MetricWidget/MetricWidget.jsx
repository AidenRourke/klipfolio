import React, {useState, useEffect} from "react"
import './MetricWidget.css'
import LineGraph from 'react-line-graph'
import NumberFormat from 'react-number-format';
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
        height: "4rem"
    };

    return <div className="Metric-widget">
        <h5 className="Metric-detail">{name}</h5>
        <NumberFormat value={latestMetricValue?.value}
                      className="Metric-detail"
                      displayType={'text'}
                      thousandSeparator={true}
                      prefix={symbol}
                      renderText={(value, props) => <h3 {...props}>{value}</h3>} />
        <LineGraph {...props}/>
    </div>
};

export default MetricWidget