package com.marcioflavio.mfinder;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import core.GLA;

@SpringBootApplication
public class MovieSongFinderApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(MovieSongFinderApplication.class, args);
	}

}
