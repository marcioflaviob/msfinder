package com.marcioflavio.mfinder.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcioflavio.mfinder.entity.Song;
import com.marcioflavio.mfinder.service.GeniusAPIService;

@RestController
public class GeniusController {

    @Autowired
    private GeniusAPIService geniusAPIService;

    @GetMapping("/search")
    public ResponseEntity<List<Song>> searchSong(@RequestParam String q) throws IOException, InterruptedException {
        return new ResponseEntity<List<Song>>(geniusAPIService.searchSong(q), HttpStatus.OK);
    }

    @GetMapping("/song/{id}")
    public Song pickSong(@PathVariable(required = true) String id) throws IOException, InterruptedException {
        return geniusAPIService.pickSong(id);
    }
    
}
