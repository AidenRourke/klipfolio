import React from "react";

import logo from "./logo.svg";
import Search from "./Search";

import "./NavBar.css"

const NavBar = () => {
    return <div className="Nav-bar">
        <img src={logo} alt="logo" className="App-logo"/>
        <Search/>
    </div>
};

export default NavBar