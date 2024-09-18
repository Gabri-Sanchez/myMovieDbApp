package com.gsanchez.mySpringApp.domain;

import java.util.List;
import com.gsanchez.mySpringApp.domain.Movie;

public class User {
    private String id;
    private String name;
    private List<Movie> favoriteMovies;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getFavoriteMovies(){
        return favoriteMovies;
    }

    public void setFavoriteMovies(List<Movie> favorites){
        favoriteMovies = favorites;
    }

}
