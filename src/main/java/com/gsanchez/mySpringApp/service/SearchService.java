package com.gsanchez.mySpringApp.service;
import java.util.List;

import com.gsanchez.mySpringApp.domain.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import reactor.core.publisher.Mono;


@Service
public class SearchService {

    private final WebClient client;

    @Autowired
    public SearchService(WebClient movieClient){
        this.client = movieClient;
    }


    public Mono<List<Movie>> handleSearch(String query, String year){
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
                        return response.bodyToMono(JsonNode.class)
                        .flatMap(body -> {
                            JsonNode results = body.get("results");
                            System.out.println("DEBUG: Raw response results: " + results);
                            try {

                                ObjectMapper mapper = new ObjectMapper();
                                List<Movie> list = mapper.readValue(
                                                results.toString(), new TypeReference<List<Movie>>() {});
                                return Mono.just(list);

                            } catch (Exception e) {
                                // TODO: handle exception
                                System.out.println("ERROR: " + e);
                                return Mono.just(List.of());
                            }
                        }
                        
                        )
                        ;

                    } else{
                        System.out.println("ERROR: " + response.statusCode() + " from API server");
                        return Mono.just(List.of());
                    }
                });
        } catch (Exception e) {
            System.out.println("ERROR: Unexpected error");
            return Mono.just(List.of());
        }
        
        
    }
}
