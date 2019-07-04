package com.example.moviedb;


import java.util.List;

public interface MoviesView {
        void addItemToSpinner(List<Genre> genresList);
        void populateListView(List<Movie> moviesList);

    }



