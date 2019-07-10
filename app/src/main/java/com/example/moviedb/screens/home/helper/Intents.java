package com.example.moviedb.screens.home.helper;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.moviedb.screens.home.screens.moviedetail.view.MovieDetailsActivity;
import com.example.moviedb.screens.home.utils.Constants;

public class Intents {

    private static Intents instance;

    private Intents(){}

    public Intent getMovieDetailIntent(Context context, @NonNull String movieId) {
        Intent intent = new Intent (context, MovieDetailsActivity.class);
        intent.putExtra(Constants.MOVIE_ID_EXTRA_KEY,movieId);
        return intent;
    }

    public static Intents getInstance() {
        if (instance == null)
            instance = new Intents();
        return instance;
    }
}
