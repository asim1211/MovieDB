package com.example.moviedb;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONResult {

    @SerializedName("results")
    @Expose
    private List<Movie> movies;



    public List<Movie> getMovies(){
        return movies;
    }
}


