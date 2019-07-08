package com.example.moviedb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GenreJSONResults {


    @SerializedName("genres")
    @Expose
    private ArrayList<Genre> genres;


    public ArrayList<Genre> getGenres(){
        return genres;
    }
}
