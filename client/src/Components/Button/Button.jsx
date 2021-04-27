import React from "react";

import "./Button.css"

const Button = ({onClick, children}) => {
    return <button onClick={onClick} className="Button">{children}</button>
};

export default Button;