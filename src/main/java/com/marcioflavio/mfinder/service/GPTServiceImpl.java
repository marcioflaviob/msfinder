package com.marcioflavio.mfinder.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GPTServiceImpl implements GPTService{

    @Value("${openai.apiKey}")
    private String apiKey;

    private final String apiUrl = "https://api.openai.com/v1/chat/completions";

    private final RestTemplate restTemplate;

    public GPTServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getAnswer(String message) {
        String requestBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},{\"role\": \"user\", \"content\": \"" + message + "\"}]}";

        String response = restTemplate.postForObject(apiUrl, getHttpEntity(requestBody), String.class);
        return response;
    }

    private HttpEntity<String> getHttpEntity(String requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(requestBody, headers);
    }
    
}
