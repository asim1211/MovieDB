package com.example.moviedb.screens.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetails {

    private MovieDetails movieDetails;

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


    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;


    @SerializedName("vote_average")
    @Expose
    private String vote_average;


    public MovieDetails (String title, String original_language, String overview, String release_date,
                         String poster_path, String backdrop_path, String vote_average){
        this.title = title;
        this.original_language = original_language;
        this.overview = overview;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.vote_average = vote_average;
    }

    public String getPoster_path() { return poster_path; }

    public String getBackdrop_path() { return backdrop_path; }

    public String getOriginal_language() { return original_language; }

    public String getOverview() { return overview; }

    public String getRelease_date() { return release_date; }

    public String getTitle() { return title; }

    public String getVote_average() { return vote_average; }

}
