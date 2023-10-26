package com.marcioflavio.mfinder.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcioflavio.mfinder.service.GeniusAPIService;

@RestController
public class GeniusController {

    @Autowired
    private GeniusAPIService geniusAPIService;

    @GetMapping("/search")
    public String searchSong(@RequestParam String query) throws IOException, InterruptedException {
        return geniusAPIService.searchSong(query);
    }

    @GetMapping("/song/{id}")
    public String pickSong(@PathVariable(required = true) String id) throws IOException, InterruptedException {
        return geniusAPIService.searchSong(id);
    }
    
}
