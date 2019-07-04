package com.example.moviedb.screens.home;


import com.example.moviedb.Genre;
import com.example.moviedb.Movie;

import java.util.List;

public interface MoviesView {

        interface View {
                void addItemToSpinner(List<Genre> genresList);
                void populateListView(List<Movie> moviesList);
        }

        interface Presenter {
                int getCurrentGenre();
                int setCurrentGenre(int currentItem);
        }

}



