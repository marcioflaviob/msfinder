package com.marcioflavio.mfinder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import com.marcioflavio.mfinder.CorsFilter;

@Configuration
@PropertySource("classpath:application.properties")
public class GeniusAPIConfig {
    
    @Value("${genius.api.key}") //Grab the value from application.properties instead of storing it in the code.
    private String apiKey;

    private String getApiKey() {
        return apiKey;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }

}
