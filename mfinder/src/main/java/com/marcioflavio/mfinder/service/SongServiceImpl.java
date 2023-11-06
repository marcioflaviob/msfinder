package com.marcioflavio.mfinder.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.marcioflavio.mfinder.entity.Song;

import core.GLA;
import genius.SongSearch.Hit;

@Service
public class SongServiceImpl implements SongService {

    @Override
    public Song addSong(String id, String title, String artist, String thumbUrl, String lyrics) {
        return new Song(id, title, artist, thumbUrl, lyrics);
    }

    
    
}
