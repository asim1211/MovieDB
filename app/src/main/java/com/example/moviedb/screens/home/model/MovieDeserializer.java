package com.example.moviedb.screens.home.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.RealmList;

public class MovieDeserializer implements JsonDeserializer<Movie> {
    @Override
    public Movie deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Movie movie = new Gson().fromJson(json.getAsJsonObject().toString(), Movie.class);
        if (movie.genre_ids != null && movie.genre_ids.size() > 0)
            movie.genre = movie.genre_ids.toString();

        return movie;
    }
}
