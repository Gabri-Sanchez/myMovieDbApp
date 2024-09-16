package com.gsanchez.mySpringApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {

    private final SearchService searchService;

    @Autowired
    public Controller(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public Mono<String> handleSearch(@RequestParam(name = "title") String query, @RequestParam(name = "year", defaultValue = "") String year
    ){
        return searchService.handleSearch(query, year);
    }
}
