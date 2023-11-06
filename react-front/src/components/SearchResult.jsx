import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import Loading from './Loading';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ResultsPage from '../ResultsPage';

import "./SearchResult.css"

export const SearchResult = ({selected, result, setLoading}) => {

    const [data, setData] = useState(null);
    const navigateTo = useNavigate();

    const onClick = async () => {
        try {
            setLoading(true);

            const response = await fetch('http://localhost:8080/results', {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
              },
              body: JSON.stringify(result),
            });

            const data = await response.json();
            setData(data);
            console.log(data); // Received the json.

            setLoading(false);
            const encodedData = encodeURIComponent(JSON.stringify(data));
            navigateTo(`/${encodedData}`);

          } catch (error) {
            console.error('Error:', error);
          }
        };
  return (
    <div className="search-result" onClick={onClick}>
        <div className="img"><img src={result.thumbnailUrl} width="50"></img></div>
        <div className="names">
            <div className="title">{result.title.toLowerCase()}</div>
            <div className="artist">{result.artist.toLowerCase()}</div>
        </div>
      </div>
  )
}
