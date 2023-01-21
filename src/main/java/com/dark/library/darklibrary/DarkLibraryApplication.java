package com.dark.library.darklibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@SpringBootApplication
public class DarkLibraryApplication {
	public static void main(String[] args) {
		SpringApplication.run(DarkLibraryApplication.class, args);
	}

}
