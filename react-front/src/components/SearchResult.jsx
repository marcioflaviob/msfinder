import React, { useState } from 'react'

import Loading from './Loading';

import "./SearchResult.css"

export const SearchResult = ({result, setLoading}) => {

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
            console.log(data); // Received the json.

            setLoading(false);

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
