package com.marcioflavio.mfinder.controller;

import java.io.IOException;
import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcioflavio.mfinder.entity.GPTRequest;
import com.marcioflavio.mfinder.entity.Movie;
import com.marcioflavio.mfinder.entity.Song;
import com.marcioflavio.mfinder.service.TMDBService;

@RestController
@RequestMapping("/movie")
public class TMDBController {

    @Autowired
    TMDBService tmdbService;

    @GetMapping
    public ResponseEntity<Movie> submit(Song song) throws IOException, InterruptedException {
        GPTRequest gptRequest = new GPTRequest();
        gptRequest.setMovieTitle("Batman");
        gptRequest.setYear("2005");
        return new ResponseEntity<Movie>(tmdbService.searchMovie(gptRequest, "pt-BR"), HttpStatus.OK);
    }
    
}
