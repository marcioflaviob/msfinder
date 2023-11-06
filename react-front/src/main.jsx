import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import ResultsPage from './ResultsPage.jsx';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './index.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path="/:data" element={<ResultsPage />} />
        <Route path="/" element={<App />} />
      </Routes>
    </Router>
  </React.StrictMode>
)
