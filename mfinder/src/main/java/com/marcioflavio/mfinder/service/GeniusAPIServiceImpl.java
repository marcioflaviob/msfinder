package com.marcioflavio.mfinder.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcioflavio.mfinder.entity.Song;

@Service
public class GeniusAPIServiceImpl implements GeniusAPIService {

    @Value("${genius.api.key}")
    private String apiKey;

    @Autowired
    SongService songService;

    @Override
    public List<Song> searchSong(String query) throws IOException, InterruptedException {
        String url = "https://api.genius.com/search?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Authorization", "Bearer " + apiKey)
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.body());
        List<Song> songs = new ArrayList<>();
        
        JsonNode hits = jsonNode.get("response").get("hits"); //Separate the songs
        for (JsonNode song : hits) { // Iterates through each node
            Long id = song.get("result").get("id").asLong(); //Get the fields and saves them.
            String title = song.get("result").get("title").asText();
            String artist = song.get("result").get("artist_names").asText();
            String thumbUrl = song.get("result").get("header_image_thumbnail_url").asText();
            String lyrics = "Not yet necessary"; //We don't need the lyrics at the moment, so to make the json file shorter, I'm not going to pass it here.

            songs.add(songService.addSong(id, title, artist, thumbUrl, lyrics));
        }
        System.out.println(songs);

        return songs;
    }

    public String getLyrics(Long id) {
        String lyrics;
        
        return lyrics;
    }

    @Override
    public Song pickSong(String id) throws IOException, InterruptedException {
        String url = "https://api.genius.com/songs/" + URLEncoder.encode(id, StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Authorization", "Bearer " + apiKey)
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        response.body();
        return null;
    }
    
}
