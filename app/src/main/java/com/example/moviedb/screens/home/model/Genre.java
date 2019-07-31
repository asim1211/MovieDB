package com.example.moviedb.screens.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Genre extends RealmObject {


    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    public transient boolean selected;


    public Genre(){}


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
