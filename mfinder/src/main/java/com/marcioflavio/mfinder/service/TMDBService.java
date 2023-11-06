package com.marcioflavio.mfinder.service;

import java.io.IOException;
import com.marcioflavio.mfinder.entity.GPTRequest;
import com.marcioflavio.mfinder.entity.Movie;

public interface TMDBService {
    
    public Movie searchMovie(GPTRequest gptRequest, String lang) throws IOException, InterruptedException;

}
