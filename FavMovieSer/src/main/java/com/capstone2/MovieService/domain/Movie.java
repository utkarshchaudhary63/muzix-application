package com.capstone2.MovieService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Movie {

    private boolean adult;
    private String backdrop_path;
    @Id
    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private String poster_path;
    private String media_type;
    private int[] genre_ids;
    private int popularity;
    private String release_date;
    private boolean video;
    private String vote_average;
    private String vote_count;




}

