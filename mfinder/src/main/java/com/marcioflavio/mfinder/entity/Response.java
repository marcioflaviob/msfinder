package com.marcioflavio.mfinder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    Song song;
    GPTRequest gptRequest;
    Movie movie;
    String lang;
    
}
