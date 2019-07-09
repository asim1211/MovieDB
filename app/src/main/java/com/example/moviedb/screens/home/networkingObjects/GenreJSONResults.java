package com.example.moviedb.screens.home.networkingObjects;

import com.example.moviedb.screens.home.objects.Genre;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GenreJSONResults {


    @SerializedName("genres")
    @Expose
    private ArrayList<Genre> genres;


    public ArrayList<Genre> getGenres(){
        return genres;
    }
}
