package com.marcioflavio.mfinder.service;

import com.marcioflavio.mfinder.entity.Song;

public interface SongService {

    public Song addSong(String id, String title, String artist, String thumbUrl, String lyrics);
    
}
