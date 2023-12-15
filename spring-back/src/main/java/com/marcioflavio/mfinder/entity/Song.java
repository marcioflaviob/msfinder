package com.marcioflavio.mfinder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    
    private String id;
    private String title;
    private String artist;
    private String thumbnailUrl;
    private String lyrics;

}
