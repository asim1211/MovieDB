package com.example.moviedb.screens.home.screens.movies;


import android.content.Intent;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.screens.home.model.Movie;

import java.util.List;

import io.realm.RealmResults;

public interface MoviesInterface {

        interface View {
                void init();
                void onMoviesReady(RealmResults<Movie> moviesList);
                void onScrollUpdateMovies(RealmResults<Movie> moviesList);
                void onGenresReady(List<Genre> genres);
                void onIntent(Intent intent);
        }

        interface Presenter {
                void init();
                RecyclerView.OnScrollListener getScrollListener(Spinner spinner, LinearLayoutManager linearLayoutManager);
                void initPageCount();
                Genre getSelectedGenre(int position);
                void getMovies(String genreId, int page);
        }

}



