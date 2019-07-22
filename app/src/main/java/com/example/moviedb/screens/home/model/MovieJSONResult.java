package com.example.moviedb.screens.home.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieJSONResult {

    @SerializedName("results")
    @Expose
    private List<Movie> movieObjects;


    public List<Movie> getMovieObjects(){
        return movieObjects;
    }
}


