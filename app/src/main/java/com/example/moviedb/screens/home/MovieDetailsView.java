package com.example.moviedb.screens.home;


import com.example.moviedb.MovieDetails;



public interface MovieDetailsView {


    interface View {
        void init();
        void populateView(MovieDetails movie);
    }

    interface Presenter {
        void init(String movieID);


    }
}
