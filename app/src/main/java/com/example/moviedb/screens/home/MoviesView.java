package com.example.moviedb.screens.home;


import com.example.moviedb.Genre;
import com.example.moviedb.Movie;

import java.util.ArrayList;
import java.util.List;

public interface MoviesView {

        interface View {
                void addItemToSpinner(ArrayList<Genre> genresList);
                void populateListView(List<Movie> moviesList);
        }

        interface Presenter {
                Genre getCurrentGenre();
                void setCurrentGenre(Genre currentItem);
        }

}



