package com.example.moviedb.screens.home.screens.movies;


import android.content.Intent;

import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.screens.home.model.Movie;

import java.util.List;

import io.realm.RealmResults;

public interface MoviesInterface {

        interface View {
                void init();
                void onMoviesReady(RealmResults<Movie> moviesList);
                void onGenresReady(List<Genre> genres);
                void onIntent(Intent intent);
        }

        interface Presenter {
                void init();
                Genre getSelectedGenre(int position);
        }

}



