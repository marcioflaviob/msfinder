import React, {useState} from 'react'

import {FaSearch} from "react-icons/fa";
import "./SearchBar.css"

export const SearchBar = ({setResults}) => {
    const [input, setInput] = useState("")

    const fetchData = (value) => {
        fetch("http://localhost:8080/search?q=" + input)
            .then((response) => response.json())
            .then((json) => {
                setResults(json);
            });
    };

    const handleChange = (value) => {
        setInput(value)
        fetchData(value)
    }
    return (

    <div><h1 className="header-title">a movie just like your favorite song?</h1>

    <div className="input-wrapper">
        <FaSearch id="search-icon" />
        <input 
            placeholder='Type to search...' 
            value={input} 
            onChange={(e) => handleChange(e.target.value)}/>
    </div>

    <footer>
        <p>by marcio flavio in 2023</p>
        <p className='links'>
            <a href='https://github.com/marcioflaviob'>github</a>
            <a> | </a>
            <a href='https://www.linkedin.com/in/marcioflavio'>linkedin</a>
        </p>
    </footer>
    
    </div>
    );
};