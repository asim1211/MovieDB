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

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("genre_ids")
    @Expose
    private RealmList<String> genre_ids;






    public Movie(){

    }

    public Movie (String title, String original_language, String overview, String release_date, String poster_path, String id, RealmList<String> genre_ids ){
        this.title = title;
        this.original_language = original_language;
        this.overview = overview;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.id = id;
        this.genre_ids = genre_ids;
    }



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
