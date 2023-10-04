package com.example.kinoxp.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PostMovieDTO {

    private int id;
    private String title;
    private String description;
    private String image;
    private int year;
    private Set<Integer> genres = new HashSet<>();

    public PostMovieDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Integer> getGenres() {
        return genres;
    }

    public void setGenres(Set<Integer> genres) {
        this.genres = genres;
    }
}
