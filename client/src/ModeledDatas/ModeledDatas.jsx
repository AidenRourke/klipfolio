import React, {useEffect, useState} from "react";
import {getModeledData} from "../Api";
import {Button} from "../Components";
import {DataModelWidget} from "../Components";

import  "./ModeledDatas.css"

const ModeledDatas = () => {
    const [modeledDatas, setModeledDatas] = useState(undefined);

    useEffect(() => {
        getModeledData()
            .then(res => res.json())
            .then(data => setModeledDatas(data))
    }, []);

    const renderContent = () => {
        return modeledDatas?._embedded.modeledDatas.map(modeledData => <DataModelWidget key={modeledData._links.self.href} name={modeledData.name}/>)
    };

    const fetchMore = (url) => {
        fetch(url)
            .then(res => res.json())
            .then(res => {
                const prevModeledDatas = modeledDatas._embedded.modeledDatas;
                const newModeledDatas = res._embedded.modeledDatas;
                res._embedded.modeledDatas = [...prevModeledDatas, ...newModeledDatas];
                setModeledDatas(res)
            })
    };

    const fetchFirst = (url) => {
        fetch(url)
            .then(res => res.json())
            .then(res => {
                setModeledDatas(res)
            })
    };

    const renderLoadLess = () => {
        if (modeledDatas?.page.number > 0) {
            return <Button onClick={() => fetchFirst(modeledDatas._links.first.href)}>Less modelled data</Button>
        }
    };

    const renderLoadMore = () => {
        if (modeledDatas?.page.number !== modeledDatas?.page.totalPages - 1) {
            return <Button onClick={() => fetchMore(modeledDatas._links.next.href)}>More modelled data</Button>
        }
    };

    return <div>
        <h3 className="Asset-title">Existing modeled data</h3>
        <div className="Modeled-data-grid">
            {renderContent()}
        </div>
        <div className="Load-more">
            {renderLoadLess()}
            {renderLoadMore()}
        </div>
    </div>
};

export default ModeledDatas;