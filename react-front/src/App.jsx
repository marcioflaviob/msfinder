import React, { useState, useEffect } from 'react'
import './App.css'
import { SearchBar } from './components/SearchBar'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { SearchResultsList } from './components/SearchResultsList';
import { LanguageSelector } from './components/LanguageSelector'
import Loading from './components/Loading'
import ResultsPage from './ResultsPage';


function App() {

  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const [selected, setSelected] = useState(false);

  return (
    <div className="App">
      {loading && <div className="App-loading">
        <Loading selected={selected} loading={loading}/>
        </div>}
      <div className="search-bar-container">
        <LanguageSelector selected={selected} setSelected={setSelected} />
        <SearchBar selected={selected} setResults={setResults} />
        <SearchResultsList selected={selected} results={results} setLoading={setLoading} />
      </div>
    </div>
  )
}

export default App
