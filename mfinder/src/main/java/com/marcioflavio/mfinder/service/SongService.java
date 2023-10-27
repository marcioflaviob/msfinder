package com.marcioflavio.mfinder.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.marcioflavio.mfinder.entity.Song;

import genius.SongSearch.Hit;

public interface SongService {

    public LinkedList<Hit> search(String query) throws IOException;
    
}
