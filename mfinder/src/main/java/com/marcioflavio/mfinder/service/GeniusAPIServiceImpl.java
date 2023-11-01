package com.marcioflavio.mfinder.service;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcioflavio.mfinder.entity.Song;

import core.ProjectInfo;

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
            String id = song.get("result").get("id").asText(); //Get the fields and saves them.
            String title = song.get("result").get("title").asText();
            String artist = song.get("result").get("artist_names").asText();
            String thumbUrl = song.get("result").get("header_image_thumbnail_url").asText();
            String lyrics = "Not yet necessary"; //We don't need the lyrics at the moment, so to make the json file shorter, I'm not going to pass it here.

            songs.add(songService.addSong(id, title, artist, thumbUrl, lyrics));
        }
        System.out.println(songs);

        return songs;
    }

    public String getLyrics(String id) {
        String lyrics;
        final String URL_HEAD = "https://genius.com/songs/";
        final String URL_TAIL = "/embed.js"; // The URL to get the lyrics

        try {
            URLConnection connection = new URL(URL_HEAD + id + URL_TAIL).openConnection();
            connection.setRequestProperty("User-Agent", ProjectInfo.VERSION);
            Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8.name()); //This is to ensure that content retrieved from the URL is encoded in UTF-8
            scanner.useDelimiter("\\A");
            StringBuilder rawLyricsBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                rawLyricsBuilder.append(scanner.next());
            }
            String rawLyrics = rawLyricsBuilder.toString();
            lyrics = rawLyrics;
        } catch (IOException e) {
            return null;
        }
        
        //Remove start
        lyrics = lyrics.replaceAll("[\\S\\s]*<div class=\\\\\\\\\\\\\"rg_embed_body\\\\\\\\\\\\\">[ (\\\\\\\\n)]*", "");
        //Remove end
        lyrics = lyrics.replaceAll("[ (\\\\\\\\n)]*<\\\\/div>[\\S\\s]*", "");
        //Remove tags between
        lyrics = lyrics.replaceAll("<[^<>]*>", "");
        //Unescape spaces
        lyrics = lyrics.replaceAll("\\\\\\\\n","[LINE BREAK]"); //The line breaks were not working in the JSON
        //Unescape '
        lyrics = lyrics.replaceAll("\\\\'", "'");
        //Unescape "
        lyrics = lyrics.replaceAll("\\\\\\\\\\\\\"", ""); //The " was confliting with the JSON, so I removed it
        return lyrics;
    }
    
}
