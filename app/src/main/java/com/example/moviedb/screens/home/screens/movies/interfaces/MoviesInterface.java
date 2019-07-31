package com.example.moviedb.screens.home.screens.movies.interfaces;


import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.screens.home.model.Movie;
import com.example.moviedb.screens.home.screens.movies.adapter.HorizontalAdapter;

import java.util.List;

import io.realm.RealmResults;

public interface MoviesInterface {

        interface View {
                void init();
                void onMoviesReady(RealmResults<Movie> moviesList);
                void onScrollUpdateMovies(RealmResults<Movie> moviesList);
                void onGenresReady(List<Genre> genres);
                void onIntent(Intent intent);
                HorizontalAdapter getGenreAdapter();
        }

        interface Presenter {
                void init();
                RecyclerView.OnScrollListener getScrollListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager);
                void resetVariables(int position);
                Genre getSelectedGenre(int position);
                void getMovies(String genreId, int page);
                void updateResultCondition(String genreId);
                void updateGenreSelection(int position);
        }

}



