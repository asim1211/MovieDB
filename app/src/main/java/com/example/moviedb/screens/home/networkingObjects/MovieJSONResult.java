package com.example.moviedb.screens.home.networkingObjects;


import com.example.moviedb.screens.home.objects.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieJSONResult {

    @SerializedName("results")
    @Expose
    private List<Movie> movies;



    public List<Movie> getMovies(){
        return movies;
    }
}


