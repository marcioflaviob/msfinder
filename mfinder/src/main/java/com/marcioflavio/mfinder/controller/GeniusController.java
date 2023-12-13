package com.marcioflavio.mfinder.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class GeniusController {

    GeniusAPIService geniusAPIService;
    GPTService gptService;
    TMDBService tmdbService;

    @GetMapping("/search")
    public ResponseEntity<List<Song>> searchSong(@RequestParam String q) throws IOException, InterruptedException { 
        return new ResponseEntity<List<Song>>(geniusAPIService.searchSong(q), HttpStatus.OK);
    }

    @PostMapping("/en")
    public ResponseEntity<Response> showResultEN(@RequestBody(required = true) Song song) throws IOException, InterruptedException{
        String lang = "en-US";
        
        song.setLyrics(geniusAPIService.getLyrics(song.getId()));

        GPTRequest gptRequest = new GPTRequest();
        gptRequest.setMovieTitle("Batman");
        gptRequest.setYear("2005");
        gptRequest.setPoint1("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate fermentum pretium. Etiam massa metus, dictum quis velit quis, suscipit viverra nunc. Etiam ante enim, scelerisque quis molestie. ");
        gptRequest.setPoint2("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate fermentum pretium. Etiam massa metus, dictum quis velit quis, suscipit viverra nunc. Etiam ante enim, scelerisque quis molestie. ");
        gptRequest.setPoint3("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate fermentum pretium. Etiam massa metus, dictum quis velit quis, suscipit viverra nunc. Etiam ante enim, scelerisque quis molestie. ");
        /*
        GPTRequest gptRequest = gptService.breakResponse(gptService.getAnswer(song, lang)); // Send the song to GPT, and formats its response.
        */
        Movie movie = tmdbService.searchMovie(gptRequest, lang);
        Response response = new Response(song, gptRequest, movie, lang);

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

     @PostMapping("/br")
    public ResponseEntity<Response> showResultBR(@RequestBody(required = true) Song song) throws IOException, InterruptedException{
        String lang = "pt-BR";
        
        song.setLyrics(geniusAPIService.getLyrics(song.getId())); //Gets the lyrics
        
        GPTRequest gptRequest = gptService.breakResponse(gptService.getAnswer(song, lang)); // Send the song to GPT, and formats its response.
        Movie movie = tmdbService.searchMovie(gptRequest, lang); //Search the movie recommended by GPT in the TMdB API.
        Response response = new Response(song, gptRequest, movie, lang);
        
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @PostMapping("/repeat")
    public ResponseEntity<Response> newMovie(@RequestBody(required = true) Response response) throws IOException, InterruptedException {
        //GPTRequest gptRequest = gptService.breakResponse(gptService.getNewMovie(response));

    /*
    */
    Thread.sleep(3000);
    GPTRequest gptRequest = new GPTRequest();
    gptRequest.setMovieTitle("Inside out");
    gptRequest.setYear("2015");
    gptRequest.setPoint1("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate fermentum pretium. Etiam massa metus, dictum quis velit quis, suscipit viverra nunc. Etiam ante enim, scelerisque quis molestie. ");
    gptRequest.setPoint2("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate fermentum pretium. Etiam massa metus, dictum quis velit quis, suscipit viverra nunc. Etiam ante enim, scelerisque quis molestie. ");
    gptRequest.setPoint3("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate fermentum pretium. Etiam massa metus, dictum quis velit quis, suscipit viverra nunc. Etiam ante enim, scelerisque quis molestie. ");

        Movie movie = tmdbService.searchMovie(gptRequest, response.getLang());

        response.setGptRequest(gptRequest);
        response.setMovie(movie);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
    
}

//INCLUDE THE CODE BELOW TO NOT USE THE GPT's API

/*GPTRequest gptRequest = new GPTRequest();
gptRequest.setMovieTitle("Batman");
gptRequest.setYear("2005");
gptRequest.setPoint1("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate fermentum pretium. Etiam massa metus, dictum quis velit quis, suscipit viverra nunc. Etiam ante enim, scelerisque quis molestie. ");
gptRequest.setPoint2("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate fermentum pretium. Etiam massa metus, dictum quis velit quis, suscipit viverra nunc. Etiam ante enim, scelerisque quis molestie. ");
gptRequest.setPoint3("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate fermentum pretium. Etiam massa metus, dictum quis velit quis, suscipit viverra nunc. Etiam ante enim, scelerisque quis molestie. ");
*/
