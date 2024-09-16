package com.gsanchez.mySpringApp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
    @Value("${MOVIE_DB_KEY}")
    private String key;
    @Bean
    public WebClient movieClient(){
        return WebClient.builder()
            .baseUrl("https://api.themoviedb.org/3")
            .defaultHeader("Authorization", "Bearer " + key)
            .build();
    }
}
