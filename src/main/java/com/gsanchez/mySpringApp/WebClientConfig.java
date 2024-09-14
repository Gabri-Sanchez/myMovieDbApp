package com.gsanchez.mySpringApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
    @Bean
    public WebClient movieClient(){
        return WebClient.builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .defaultHeader("Authorization:", "")
            .build();
    }
}
