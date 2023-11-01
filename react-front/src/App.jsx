import React, { useState, useEffect } from 'react'
import './App.css'
import { SearchBar } from './components/SearchBar'
import { SearchResultsList } from './components/SearchResultsList';
import { LanguageSelector } from './components/LanguageSelector'
import Loading from './components/Loading'


function App() {

  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);
  console.log(loading);

  return (
    <div className="App">
      {loading && <div className="App-loading">
        <Loading loading={loading}/>
        </div>}
      <div className="search-bar-container">
        <LanguageSelector />
        <SearchBar setResults={setResults} />
        <SearchResultsList results={results} setLoading={setLoading} />
      </div>
    </div>
  )
}

export default App
