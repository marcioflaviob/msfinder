package com.marcioflavio.mfinder.service;

import org.springframework.stereotype.Service;

import com.marcioflavio.mfinder.entity.Song;

@Service
public class SongServiceImpl implements SongService {

    @Override
    public Song addSong(String id, String title, String artist, String thumbUrl, String lyrics) {
        return new Song(id, title, artist, thumbUrl, lyrics);
    }

    
    
}
