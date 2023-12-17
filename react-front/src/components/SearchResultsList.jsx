import React from 'react'

import "./SearchResultsList.css"
import { SearchResult } from './SearchResult'

export const SearchResultsList = ({ selected, results, setLoading }) => {
  return (
    <div className="results-list">
      {results.map((result, id) => {
        return <SearchResult selected={selected} result={result} setLoading={setLoading} key={id} />;
      })}
    </div>
  );
};
