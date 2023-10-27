import React, {useState} from 'react'

import {FaSearch} from "react-icons/fa";
import "./SearchBar.css"

export const SearchBar = ({setResults}) => {
    const [input, setInput] = useState("")

    const fetchData = (value) => {
        fetch("http://localhost:8080/songsearch?q=" + input)
            .then((response) => response.json())
            .then((json) => {
                setResults(json);
                console.log(json);
            });
    };

    const handleChange = (value) => {
        setInput(value)
        fetchData(value)
    }
    return (
    <div className="input-wrapper">
        <FaSearch id="search-icon" />
        <input 
            placeholder='Type to search...' 
            value={input} 
            onChange={(e) => handleChange(e.target.value)}/>
    </div>
    );
};