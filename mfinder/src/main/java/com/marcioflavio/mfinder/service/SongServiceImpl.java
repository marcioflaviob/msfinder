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

    public LinkedList<Hit> search(String query) throws IOException { //Searches for a song and return the 4 closest matches
        GLA gla = new GLA();
        return gla.search(query).getHits();
    }

    @Override
    public Song addSong(Long id, String title, String artist, String thumbUrl, String lyrics) {
        return new Song(id, title, artist, thumbUrl, lyrics);
    }

    
    
}
