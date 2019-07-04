package com.example.moviedb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("title")
    @Expose
    private String title;

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

    public Movie (String title, String original_language, String overview, String release_date, String poster_path){
        this.title = title;
        this.original_language = original_language;
        this.overview = overview;
        this.release_date = release_date;
        this.poster_path = poster_path;
    }

    public String getTitle(){
        return title;
    }

    public String getOriginal_language(){ return original_language; }

    public String getOverview(){
        return overview;
    }

    public String getRelease_date(){ return release_date; }

    public String getPoster_path() {
        return poster_path;
    }
}
