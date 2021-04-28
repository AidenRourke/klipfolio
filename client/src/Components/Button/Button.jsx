import React from "react";

import "./Button.css"

const Button = ({onClick, children, className = ""}) => {
    return <button onClick={onClick} className={"Button" + className}>{children}</button>
};

export default Button;