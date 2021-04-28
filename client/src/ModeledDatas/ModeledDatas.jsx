import React, {useEffect, useState} from "react";
import {getModeledData} from "../Api";
import {Button} from "../Components";
import {DataModelWidget} from "../Components";

const ModeledDatas = () => {
    const [modeledDatas, setModeledDatas] = useState(undefined);

    useEffect(() => {
        getModeledData()
            .then(res => res.json())
            .then(data => setModeledDatas(data))
    }, []);

    const renderContent = () => {
        return modeledDatas?._embedded.modeledDatas.map(modeledData => <DataModelWidget name={modeledData.name}/>)
    };

    const fetchMore = (url) => {
        fetch(url)
            .then(res => res.json())
            .then(newData => {
                const prevModeledDatas = modeledDatas._embedded.modeledDatas;
                const newModeledDatas = newData._embedded.modeledDatas;
                const updatedModeledDatas = [...prevModeledDatas, ...newModeledDatas];
                const updatedData = newData;
                updatedData._embedded.modeledDatas = updatedModeledDatas;
                setModeledDatas(updatedData)
            })
    };

    const renderLoadMore = () => {
        if (modeledDatas?._links?.next) {
            return <Button onClick={() => fetchMore(modeledDatas._links.next.href)}>More modelled data</Button>
        }
    };

    return <div>
        <h3 className="Asset-title">Existing modelled data</h3>
        <div className="Asset-container">
            {renderContent()}
        </div>
        <div className="Load-more">
            {renderLoadMore()}
        </div>
    </div>
};

export default ModeledDatas;