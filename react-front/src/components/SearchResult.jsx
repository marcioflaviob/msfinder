import React from 'react'

import "./SearchResult.css"

export const SearchResult = ({result}) => {
  return (
    <div className="search-result" onClick={(e) => alert(`You clicked on ${result.title}`)}>
        <div className="img"><img src={result.thumbnailUrl} width="50"></img></div>
        <div className="names">
            <div className="title">{result.title.toLowerCase()}</div>
            <div className="artist">{result.artist.toLowerCase()}</div>
        </div>
        </div>
  )
}
