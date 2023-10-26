package com.marcioflavio.mfinder;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import core.GLA;

@SpringBootApplication
public class MovieSongFinderApplication {

	public static void main(String[] args) throws IOException {
		//SpringApplication.run(MovieSongFinderApplication.class, args);
        GLA gla = new GLA();
        System.out.println("Searching...");
        long startMs = System.currentTimeMillis();
        System.out.println(gla.search("lucid dreams").getHits().get(0).fetchLyrics());
        System.out.println(System.currentTimeMillis() - startMs + "ms");
	}

}
