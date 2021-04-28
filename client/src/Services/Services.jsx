import React, {useState, useEffect} from "react"
import {ServiceWidget} from "../Components";

import "./Services.css"
import Button from "../Components/Button/Button";
import {getServices} from "../Api";

const Metrics = () => {
    const [services, setServices] = useState(undefined);

    useEffect(() => {
        getServices()
            .then(res => res.json())
            .then(data => setServices(data))
    }, []);

    const renderContent = () => {
        return services?._embedded.services.map(service => <ServiceWidget key={service._links.self.href}
                                                                          name={service.name}
                                                                          iconUrl={service.iconUrl}/>)
    };

    const fetchMore = (url) => {
        fetch(url)
            .then(res => res.json())
            .then(newData => {
                const prevServices = services._embedded.services;
                const newServices = newData._embedded.services;
                const updatedServices = [...prevServices, ...newServices];
                const updatedData = newData;
                updatedData._embedded.services = updatedServices;
                setServices(updatedData)
            })
    };

    const renderLoadMore = () => {
        if (services?._links?.next) {
            return <Button onClick={() => fetchMore(services._links.next.href)}>More Services</Button>
        }
    };

    return <div>
        <h3 className="Asset-title">Recommended Services</h3>
        <div className="Asset-container">
            {renderContent()}
        </div>
        <div className="Load-more">
            {renderLoadMore()}
        </div>
    </div>
};

export default Metrics;
