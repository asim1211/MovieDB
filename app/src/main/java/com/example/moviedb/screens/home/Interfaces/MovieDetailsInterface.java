package com.example.moviedb.screens.home.Interfaces;


import com.example.moviedb.screens.home.objects.MovieDetails;



public interface MovieDetailsInterface {

    interface View {
        void init();
        void populateView(MovieDetails movie);
    }

    interface Presenter {
        void init(String movieID);
    }
}
