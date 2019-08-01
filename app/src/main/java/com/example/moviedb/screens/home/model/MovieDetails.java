package com.example.moviedb.screens.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MovieDetails extends RealmObject {





    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;

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




    public MovieDetails(){}

    public String getPoster_path() { return poster_path; }

    public String getBackdrop_path() { return backdrop_path; }

    public String getOriginal_language() { return original_language; }

    public String getOverview() { return overview; }

    public String getRelease_date() { return release_date; }

    public String getTitle() { return title; }

    public String getVote_average() { return vote_average; }

    public String getId() { return id; }
}
