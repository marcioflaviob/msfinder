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
    
    private String title;
    private String titleFeat;
    private String thumbnailUrl;
    private String artist;
    private String lyrics;

}
