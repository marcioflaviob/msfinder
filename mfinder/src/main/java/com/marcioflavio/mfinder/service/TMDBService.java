package com.marcioflavio.mfinder.service;

import java.io.IOException;
import java.net.http.HttpRequest;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcioflavio.mfinder.entity.GPTRequest;
import com.marcioflavio.mfinder.entity.Movie;

public interface TMDBService {
    
    public Movie searchMovie(GPTRequest gptRequest) throws IOException, InterruptedException;

}
