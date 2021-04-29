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
            .then(res => {
                const prevServices = services._embedded.services;
                const newServices = res._embedded.services;
                res._embedded.services = [...prevServices, ...newServices];
                setServices(res)
            })
    };

    const fetchFirst = (url) => {
        fetch(url)
            .then(res => res.json())
            .then(res => {
                setServices(res)
            })
    };

    const renderLoadLess = () => {
        if (services?.page.number > 0) {
            return <Button onClick={() => fetchFirst(services._links.first.href)}>Less Services</Button>
        }
    };

    const renderLoadMore = () => {
        if (services?.page.number !== services?.page.totalPages - 1) {
            return <Button onClick={() => fetchMore(services._links.next.href)}>More Services</Button>
        }
    };

    return <div>
        <h3 className="Asset-title">Recommended Services</h3>
        <div className="Services-grid">
            {renderContent()}
        </div>
        <div className="Load-more">
            {renderLoadLess()}
            {renderLoadMore()}
        </div>
    </div>
};

export default Metrics;
