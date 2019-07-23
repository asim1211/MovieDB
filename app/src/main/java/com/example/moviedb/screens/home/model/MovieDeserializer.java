package com.example.moviedb.screens.home.model;

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


        final JsonObject jsonObject = json.getAsJsonObject();

        final String title = jsonObject.get("title").toString().replaceAll("^\"|\"$", "");
        final String id = jsonObject.get("id").toString().replaceAll("^\"|\"$", "");
        final String original_language = jsonObject.get("original_language").toString().replaceAll("^\"|\"$", "");;
        final String overview = jsonObject.get("overview").toString().replaceAll("^\"|\"$", "");
        final String release_date = jsonObject.get("release_date").toString().replaceAll("^\"|\"$", "");
        final String poster_path = jsonObject.get("poster_path").toString().replaceAll("^\"|\"$", "");
        final JsonArray genre_ids = jsonObject.get("genre_ids").getAsJsonArray();



        final Movie movie = new Movie();
        movie.setTitle(title);
        movie.setId(id);
        movie.setOriginal_language(original_language);
        movie.setOverview(overview);
        movie.setRelease_date(release_date);
        movie.setPoster_path(poster_path);
        movie.setGenre_ids(genre_ids.toString().replaceAll("^\"|\"$", ""));


        return movie;
    }
}
