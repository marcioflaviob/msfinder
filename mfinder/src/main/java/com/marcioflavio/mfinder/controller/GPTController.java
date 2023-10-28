package com.marcioflavio.mfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcioflavio.mfinder.entity.GPTRequest;
import com.marcioflavio.mfinder.entity.Song;
import com.marcioflavio.mfinder.service.GPTService;

@RestController
@RequestMapping("/api/chat")
public class GPTController {
    
    @Autowired
    private final GPTService gptService;

    public GPTController(GPTService gptService) {
        this.gptService = gptService;
    }
/*
    @PostMapping
    public String submit(Song song) {
        String songinfo = "";
        return gptService.getAnswer(songinfo);
    }*/

    @PostMapping
    public String chat(@RequestBody GPTRequest gptRequest) {
        //String message = gptRequest.getMessage();
        //System.out.println(gptRequest.getMessage());
        return null;
    }
}
