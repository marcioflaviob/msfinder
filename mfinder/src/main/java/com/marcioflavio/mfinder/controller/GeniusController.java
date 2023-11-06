package com.marcioflavio.mfinder.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.marcioflavio.mfinder.entity.GPTRequest;
import com.marcioflavio.mfinder.entity.Movie;
import com.marcioflavio.mfinder.entity.Response;
import com.marcioflavio.mfinder.entity.Song;
import com.marcioflavio.mfinder.service.GPTService;
import com.marcioflavio.mfinder.service.GeniusAPIService;
import com.marcioflavio.mfinder.service.TMDBService;

@RestController
public class GeniusController {

    @Autowired
    private GeniusAPIService geniusAPIService;

    @Autowired
    private GPTService gptService;

    @Autowired
    private TMDBService tmdbService;

    @GetMapping("/search")
    public ResponseEntity<List<Song>> searchSong(@RequestParam String q) throws IOException, InterruptedException {
        return new ResponseEntity<List<Song>>(geniusAPIService.searchSong(q), HttpStatus.OK);
    }

    @PostMapping("/results")
    public ResponseEntity<Response> showResult(@RequestBody(required = false) Song song) throws IOException, InterruptedException{
        
        song.setLyrics(geniusAPIService.getLyrics(song.getId()));
        
        GPTRequest gptRequest = new GPTRequest();
        gptRequest.setMovieTitle("Batman");
        gptRequest.setYear("2005");
        gptRequest.setPoint1("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate fermentum pretium. Etiam massa metus, dictum quis velit quis, suscipit viverra nunc. Etiam ante enim, scelerisque quis molestie. ");
        gptRequest.setPoint2("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate fermentum pretium. Etiam massa metus, dictum quis velit quis, suscipit viverra nunc. Etiam ante enim, scelerisque quis molestie. ");
        gptRequest.setPoint3("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate fermentum pretium. Etiam massa metus, dictum quis velit quis, suscipit viverra nunc. Etiam ante enim, scelerisque quis molestie. ");
       
        //GPTRequest gptRequest = gptService.breakResponse(gptService.getAnswer(song)); // Send the song to GPT, and formats its response.
        
        Movie movie = tmdbService.searchMovie(gptRequest);
        
        Response response = new Response(song, gptRequest, movie);

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
    
}
