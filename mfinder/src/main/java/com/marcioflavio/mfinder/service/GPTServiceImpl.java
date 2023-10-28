package com.marcioflavio.mfinder.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcioflavio.mfinder.entity.GPTRequest;
import com.marcioflavio.mfinder.entity.Song;

@Service
public class GPTServiceImpl implements GPTService{

    private String systemMessage = "You will work as a movie curator. Recommend me a movie based on the lyrics and mood of a song informed by the user." +
    "You should also inform 3 points justifying why this movie is similar to the song, the last point has to be related to the rhythm of the song and the vibe and mood of the movie, keep each point with 200 characters max and remember not to spoil the movie plot." + 
    "You should answer in a json format, separating the movie name, year and separate each point into a different json node." + 
    "The name of the each comparison point in the json should be 1, 2 and 3, the name of the json node for the movie title will be movie_name, and the json node for the year will be called movie_year. Here's an example of comparisons between a song and a movie: 1. Emotional Journey and Family Bonds: Like in the song, the movie delves into personal struggles and the healing power of music." +
    "It explores the complexities of relationships and the impact of family on one's life journey. 2. Artistic Expression Through Music: The protagonist in the movie, like Lukas Graham in the song, finds solace and purpose in expressing themselves through music. Both works highlight how songwriting becomes a medium to process emotions and experiences." + 
    "3. Rhythm and Vibe Connection: The movie's soundtrack has a heartfelt, acoustic quality that mirrors the emotional depth of the song. The rhythm of the music, coupled with the film's narrative, creates a resonant and uplifting atmosphere that aligns with the mood of 'Happy Home'. IMPORTANT: You HAVE TO reply in the language: PORTUGUÊS BRASILEIRO. The original language of the movie doesn't need to be in this language.";

    @Value("${openai.apiKey}")
    private String apiKey;

    private final String apiUrl = "https://api.openai.com/v1/chat/completions";

    private final RestTemplate restTemplate;

    public GPTServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getAnswer(Song song) {
        String requestBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"system\", \"content\": \"" + systemMessage + "\"},{\"role\": \"user\", \"content\": \"" + formatMessage(song) + "\"}]}";
        
        System.out.println(requestBody);
        
        String response = restTemplate.postForObject(apiUrl, getHttpEntity(requestBody), String.class);
        return response;
    }

    public String formatMessage (Song song) {
        String message = "Song Title: " + song.getTitle() + 
        " Artist: " + song.getArtist() + 
        " Lyrics: " + song.getLyrics();
        return message;
    }

    @Override
    public GPTRequest breakResponse (String response) throws JsonMappingException, JsonProcessingException{
        System.out.println(response);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response); //Converts the string into JsonNode
        JsonNode gptMovie = objectMapper.readTree(jsonNode.get("choices").get(0).get("message").get("content").asText());
        GPTRequest gptRequest = new GPTRequest();


        gptRequest.setMovieTitle(gptMovie.get("movie_name").asText());
        System.out.println(gptRequest.getMovieTitle());
        gptRequest.setYear(gptMovie.get("movie_year").asText());
        gptRequest.setPoint1(gptMovie.get("1").asText());
        gptRequest.setPoint2(gptMovie.get("2").asText());
        gptRequest.setPoint3(gptMovie.get("3").asText());

        return gptRequest;
    }

    private HttpEntity<String> getHttpEntity(String requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(requestBody, headers);
    }
    
}
