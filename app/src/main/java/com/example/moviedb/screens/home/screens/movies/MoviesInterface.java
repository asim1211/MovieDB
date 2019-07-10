package com.example.moviedb.screens.home.screens.movies;


import android.content.Intent;

import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.screens.home.model.Movie;

import java.util.ArrayList;
import java.util.List;

public interface MoviesInterface {

        interface View {
                void init();
                void onMoviesReady(List<Movie> moviesList);
                void onGenresReady(ArrayList<Genre> genres);
                void onIntent(Intent intent);
        }

        interface Presenter {
                void init();
                Genre getSelectedGenre(int position);
        }

}



