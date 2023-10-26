package com.marcioflavio.mfinder.service;

import java.io.IOException;

public interface GeniusAPIService {

    public String searchSong(String query) throws IOException, InterruptedException;
    public String pickSong(String id) throws IOException, InterruptedException;

}
