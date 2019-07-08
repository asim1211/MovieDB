package com.example.moviedb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetails {


    @SerializedName("original_title")
    @Expose
    private String original_title;

    @SerializedName("original_language")
    @Expose
    private String original_language;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("release_date")
    @Expose
    private String release_date;

    @SerializedName("poster_path")
    @Expose
    private String poster_path;


    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;



}
