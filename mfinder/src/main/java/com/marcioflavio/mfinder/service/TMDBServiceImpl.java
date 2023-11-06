package com.marcioflavio.mfinder.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcioflavio.mfinder.entity.GPTRequest;
import com.marcioflavio.mfinder.entity.Movie;

@Service
public class TMDBServiceImpl implements TMDBService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Override
    public Movie searchMovie(GPTRequest gptRequest) throws IOException, InterruptedException {

        String movieTitle = URLEncoder.encode(gptRequest.getMovieTitle(), StandardCharsets.UTF_8); //Convert the special characters
        String year = "%20&year=" + gptRequest.getYear(); //%20 equals to a space
        String lang = "&language=en-US";
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.themoviedb.org/3/search/movie?query=" + movieTitle + year + lang))
        .header("accept", "application/json")
        .header("Authorization", "Bearer " + apiKey)
        .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode firstMovie = objectMapper.readTree(response.body()).get("results").get(0);

        Movie movie = new Movie();
        movie.setTitle(firstMovie.get("title").asText());
        movie.setYear(firstMovie.get("release_date").asText().substring(0, 4)); //Get only the year
        movie.setOverview(firstMovie.get("overview").asText());
        movie.setPosterURL("https://image.tmdb.org/t/p/original" + firstMovie.get("poster_path").asText());

        return movie;
    }
    
}
