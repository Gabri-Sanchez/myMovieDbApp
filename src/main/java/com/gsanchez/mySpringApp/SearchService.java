package com.gsanchez.mySpringApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class SearchService {

    private final WebClient client;

    @Autowired
    public SearchService(WebClient movieClient){
        this.client = movieClient;
    }


    public Mono<String> handleSearch(String query, String year){
        try {
            Integer.parseInt(year);
            year = "?year=" + year;
        } catch (Exception e) {
            year = "";
        }
        try {
            return client.get()
                .uri("/search/movie?query=" + query + year)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)){
                        return response.bodyToMono(String.class);
                    } else{
                        System.out.println("ERROR: " + response.statusCode() + " from API server");
                        return Mono.just("");
                    }
                });
        } catch (Exception e) {
            System.out.println("ERROR: Unexpected error");
            return Mono.just("");
        }
        
        
    }
}
