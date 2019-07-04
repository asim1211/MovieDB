package com.example.moviedb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenreJSONResults {


    @SerializedName("genres")
    @Expose
    private List<Genre> genres;



    public List<Genre> getGenres(){
        return genres;
    }
}
