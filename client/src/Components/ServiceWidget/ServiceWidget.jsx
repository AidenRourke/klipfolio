import React from "react"

import "./ServiceWidget.css"

const ServiceWidget = ({iconUrl, name}) => {
  return <div className="Service-widget">
    <h5 className="Service-name">{name}</h5>
    <div className="Circle" title={name}>
      <img className="Service-icon" src={iconUrl} alt={`${name} icon`} />
    </div>
  </div>
};

export default ServiceWidget;