import React from "react"
import logo from './logo.svg';
import './App.css';

import Metrics from "./Metrics";
import Services from "./Services"
import {Search} from "./Components";

function App() {

    return (
        <div className="App">
            <div className="Nav-bar">
                <img src={logo} alt="logo" className="App-logo"/>
                <Search/>
            </div>
            <div className="Content">
                <Metrics/>
                <Services/>
            </div>
        </div>
    );
}

export default App;
