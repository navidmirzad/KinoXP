package com.example.kinoxp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Movie {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // API har id??
    private String title;
    private String description;
    private String image;
    private int year;
    //private MovieCatagory movieCatagory;  API KAN GIVE GENRE??


}
