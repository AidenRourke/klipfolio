import React, {useState} from "react"
import './App.css';

import Metrics from "./Metrics";
import Services from "./Services"
import ModeledDatas from "./ModeledDatas"
import NavBar from "./NavBar"

function App() {
    return (
        <div className="App">
            <NavBar/>
            <div className="Content">
                <Metrics/>
                <Services/>
                <ModeledDatas/>
            </div>
        </div>
    );
}

export default App;
