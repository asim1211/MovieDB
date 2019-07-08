package com.example.moviedb.screens.home;


import android.view.View;

import com.example.moviedb.Genre;
import com.example.moviedb.Movie;

import java.util.ArrayList;
import java.util.List;

public interface MoviesView {

        interface View {
                void init();
                void populateListView(List<Movie> moviesList);
                void onGenresReady(ArrayList<Genre> genres);
        }

        interface Presenter {
                void init();
                Genre getSelectedGenre(int position);
        }

}



