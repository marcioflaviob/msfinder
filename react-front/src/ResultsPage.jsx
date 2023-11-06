import React, { useEffect, useState } from 'react' 
import { useParams } from 'react-router-dom';
import './ResultsPage.css'

export const ResultsPage = ({ match }) => {
  const [data, setData] = useState(null);
  const params = useParams();

  useEffect(() => {
    // Decode the JSON string.
    const decodedData = decodeURIComponent(params.data);
  
    // Save the decoded JSON object into a variable.
    setData(JSON.parse(decodedData));
  }, [params.data]); // Only re-run this effect if params.data changes
  console.log(data);

  return (
    <div className="results-page">
      <h2>chosen song</h2>
      <div className="song-container">
      {data && (
        <>
            <div className="img-song"><img src={data.song.thumbnailUrl} width="70"></img></div>
            <div className="names">
              <div className="title">{data.song.title.toLowerCase()}</div>
              <div className="artist">{data.song.artist.toLowerCase()}</div>
            </div>
          </>
        )}
      </div>
      <h2>a movie you should watch</h2>
      <div className="movie-container">
      {data && (
        <>
      <div className="gpt-response">
        <div className="poster-title">
          <div className="img"><img src={data.movie.posterURL} width="250"></img></div>
          <div className="title">{data.movie.title} ({data.movie.year})</div>
        </div>
        <div className="all-points">
          <h3>why are they similar?</h3>
          <div className="points">{data.gptRequest.point1}</div>
          <div className="points">{data.gptRequest.point2}</div>
          <div className="points">{data.gptRequest.point3}</div>
        </div>
        </div>  
        
        <div className="overview">
          <h1>about the movie</h1>
          <h4>{data.movie.overview}</h4>
        </div>
        </>
      )}
      </div>
    </div>
  )
}

export default ResultsPage;