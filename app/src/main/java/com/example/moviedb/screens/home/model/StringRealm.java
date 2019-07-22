package com.example.moviedb.screens.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class StringRealm extends RealmObject {

    @SerializedName("value")
    @Expose
    public String value;

    public StringRealm(){}

    public StringRealm(String value) {
        this.value = value;
    }
}
