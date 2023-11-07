package com.marcioflavio.mfinder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.marcioflavio.mfinder.entity.GPTRequest;
import com.marcioflavio.mfinder.entity.Response;
import com.marcioflavio.mfinder.entity.Song;

public interface GPTService {

    public String getAnswer(Song song, String lang);
    public String getNewMovie(Response response) throws JsonProcessingException;
    public GPTRequest breakResponse (String response) throws JsonMappingException, JsonProcessingException;
    
}
