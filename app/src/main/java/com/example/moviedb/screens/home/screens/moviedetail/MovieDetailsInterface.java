package com.example.moviedb.screens.home.screens.moviedetail;


import android.graphics.drawable.Drawable;

import com.bumptech.glide.RequestBuilder;
import com.example.moviedb.screens.home.model.MovieDetails;



public interface MovieDetailsInterface {

    interface View {
        void onMovieReady(MovieDetails movie);
        void onBackDropReady(RequestBuilder<Drawable> requestBuilder);
        void onPosterReady(RequestBuilder<Drawable> requestBuilder);
    }

    interface Presenter {
        void init();
    }
}
