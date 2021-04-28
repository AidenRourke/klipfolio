import React from "react";

import icon from "./data-modelling.svg";

import "./DataModelWidget.css"

const DataModelWidget = ({name}) => {
    return <div className="Data-model-container">
        <h3 className="Data-model-name">{name}</h3>
        <img className="Data-model-icon" src={icon} alt="modelled data icon" />
    </div>
};

export default DataModelWidget;