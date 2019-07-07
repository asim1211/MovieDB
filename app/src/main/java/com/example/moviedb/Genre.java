package com.example.moviedb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre {


    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    public Genre(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean compareTo(Genre genre) {
        return genre.getId().equals(id);
    }
}
