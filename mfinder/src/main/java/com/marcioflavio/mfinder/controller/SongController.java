package com.marcioflavio.mfinder.controller;

import java.io.IOException;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcioflavio.mfinder.service.SongService;

import genius.SongSearch.Hit;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/songsearch")
public class SongController {

    @Autowired
    SongService songService;

    @GetMapping
    public ResponseEntity<LinkedList<Hit>> searchSongs(@RequestParam String q, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        return new ResponseEntity<LinkedList<Hit>>(songService.search(q), HttpStatus.OK);
    }
    
}
