package com.marcioflavio.mfinder.service;

import java.io.IOException;
import java.util.LinkedList;
import com.marcioflavio.mfinder.entity.Song;

import genius.SongSearch.Hit;

public interface SongService {

    public Song addSong(String id, String title, String artist, String thumbUrl, String lyrics);
    
}
