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
import com.marcioflavio.mfinder.entity.Response;
import com.marcioflavio.mfinder.entity.Song;
import com.marcioflavio.mfinder.service.GPTService;
import com.marcioflavio.mfinder.service.GeniusAPIService;

@RestController
public class GeniusController {

    @Autowired
    private GeniusAPIService geniusAPIService;

    @Autowired
    private GPTService gptService;

    @GetMapping("/search")
    public ResponseEntity<List<Song>> searchSong(@RequestParam String q) throws IOException, InterruptedException {
        return new ResponseEntity<List<Song>>(geniusAPIService.searchSong(q), HttpStatus.OK);
    }

    @PostMapping("/results")
    public ResponseEntity<Response> showResult(@RequestBody(required = false) Song song) throws JsonMappingException, JsonProcessingException{
        Response response = new Response();
        GPTRequest gptRequest = new GPTRequest();

        song.setLyrics(geniusAPIService.getLyrics(song.getId()));
        response.setSong(song);
        //gptRequest.setMessage(gptService.getAnswer(song));
        gptRequest = gptService.breakResponse(gptService.getAnswer(song)); // Send the song to GPT, and formats its response.
        response.setGptRequest(gptRequest);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
    
}
