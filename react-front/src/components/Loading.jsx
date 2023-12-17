import React from 'react'
import "./Loading.css"
import RotateLoader from "react-spinners/RotateLoader";

const Loading = ({ selected }) => {
  return <div>
    <RotateLoader
      color={"#000000"}
      size={17}
      aria-label="Loading Spinner"
      data-testid="loader"
    />
    <div className="loading-text">{selected ? "escolhendo o melhor filme baseado na sua música" : "choosing the best movie based on your song"}</div>
  </div>;
};

export default Loading;
