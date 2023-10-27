package com.marcioflavio.mfinder.service;

import java.io.IOException;
import java.util.List;

import com.marcioflavio.mfinder.entity.Song;

public interface GeniusAPIService {

    public List<Song> searchSong(String query) throws IOException, InterruptedException;
    public Song pickSong(String id) throws IOException, InterruptedException;

}
