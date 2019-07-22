package com.example.moviedb.screens.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Movie extends RealmObject {

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

    @SerializedName("genre_ids")
    @Expose
    public RealmList<String> genre_ids;

    public String genre;

    public Movie() {}

    public String getTitle(){
        return title;
    }

    public String getOriginal_language(){ return original_language; }

    public String getOverview(){
        return overview;
    }

    public String getRelease_date(){ return release_date; }

    public String getPoster_path() { return poster_path; }

    public String getID(){
        return id;
    }

    public RealmList<String> getGenre_ids() {
        return genre_ids;
    }
}
