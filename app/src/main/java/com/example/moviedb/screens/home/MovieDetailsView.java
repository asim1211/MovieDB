package com.example.moviedb.screens.home;

import com.example.moviedb.Genre;
import com.example.moviedb.Movie;
import com.example.moviedb.MovieDetails;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsView {


    interface View {
        void init();
        void populateListView(MovieDetails movie);
    }

    interface Presenter {
        void init();
    }
}
